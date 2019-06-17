package com.wp.modules.catalogue.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wp.common.utils.PageUtils;
import com.wp.common.utils.Query;
import com.wp.modules.catalogue.dao.CatalogueDao;
import com.wp.modules.catalogue.entity.Catalogue;
import com.wp.modules.catalogue.entity.CatalogueCategory;
import com.wp.modules.catalogue.entity.CatalogueImportModel;
import com.wp.modules.catalogue.entity.CatalogueItem;
import com.wp.modules.catalogue.service.CatalogueCategoryService;
import com.wp.modules.catalogue.service.CatalogueItemService;
import com.wp.modules.catalogue.service.CatalogueService;
import com.wp.modules.catalogue.utils.CatalogueCodeUtils;
import com.wp.modules.catalogue.utils.QueryField;
import com.wp.modules.manage.entity.ManageAuditEntity;
import com.wp.modules.manage.service.ManageAuditService;
import com.wp.modules.resource_register.entity.TResourcesInfo;
import com.wp.modules.resource_register.service.TResourcesInfoService;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;

@Service
public class CatalogueServiceImpl extends ServiceImpl<CatalogueDao, Catalogue> implements CatalogueService {

	@Autowired
	private CatalogueCategoryService catalogueCategoryService;
	@Autowired
	private CatalogueItemService catalogueItemService;
	@Autowired
	private TResourcesInfoService tResourcesInfoService;
    @Autowired
    private ManageAuditService manageAuditService;
	
	@Override
	public Map<String, Integer> totals(String catalogueId) {
		
		Map<String, Integer> resultMap = new HashMap<>();
		//1.查询目录总数
		resultMap.put("catalogTotals", catalogueCategoryService.selectCount(new EntityWrapper<CatalogueCategory>().eq(QueryField.CATALOGUE_ID, catalogueId).eq(QueryField.STATUS, 1)));
		resultMap.put("resourceTotals", 0);
		resultMap.put("itemTotals", 0);
		
		//根据目录ID查询资源目录列表(审核通过已经发布的）
		List<Catalogue> rcInfos = this.selectList(
				new EntityWrapper<Catalogue>()
					.eq(QueryField.CATALOGUE_CATEGORY_ID, catalogueId)
					.eq(QueryField.STATUS, 3)
					.eq(QueryField.DELETED, false)
				);
		
		if(rcInfos != null && !rcInfos.isEmpty()) {
			List<String> ids = rcInfos.stream().map(Catalogue::getCatalogueCode).collect(Collectors.toList());
			if(ids != null && !ids.isEmpty()) {
				//2.查询资源总数
				resultMap.put("resourceTotals", tResourcesInfoService.selectCount(new EntityWrapper<TResourcesInfo>().in(QueryField.RESOURCE_CATALOGUE_CODE, ids).eq(QueryField.STATUS, 3)));
				//3.查询信息项总数
				resultMap.put("itemTotals", catalogueItemService.selectCount(new EntityWrapper<CatalogueItem>().in(QueryField.RESOURCE_CATALOGUE_CODE, ids)));
			}
		}
		
		return resultMap;
	}

	/**
	 * 分页查询列表
	 */
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String name = (String)params.get("name"); //资源目录名称
		String catalogueCode = (String)params.get("catalogueCode"); //资源目录代码
		String type = (String)params.get("type"); //资源目录类型
		String offerDepartment = (String)params.get("offerDepartment"); //提供方
		String status = (String)params.get("status"); //状态：0=待审核，1=审核中，2=未通过，3=已发布

		Page<Catalogue> page = this.selectPage(
				new Query<Catalogue>(params).getPage(),
				new EntityWrapper<Catalogue>()
					.like(StringUtils.isNotBlank(name), QueryField.NAME, name)
					.like(StringUtils.isNotBlank(catalogueCode), QueryField.CATALOGUE_CODE, catalogueCode)
					.eq(StringUtils.isNotBlank(type), QueryField.TYPE, type)
					.like(StringUtils.isNotBlank(offerDepartment), QueryField.OFFER_DEPARTMENT, offerDepartment)
					.eq(StringUtils.isNotBlank(status), QueryField.STATUS, status)
					.eq(QueryField.DELETED, false)
		);

		return new PageUtils(page);
	}

	/**
	 * 逻辑：先整体删除信息项，再重新插入
	 */
	@Override
	@Transactional
	public void save(Catalogue catalogue) {
		
		//如果根据id查询为空，则默认没有此条数据，设置创建时间; 反之，则设置更新时间
		Catalogue rci = this.selectById(catalogue.getCatalogueCode());
		if(rci == null) {
			catalogue.setCatalogueCode(CatalogueCodeUtils.getcatalogueCode(catalogue.getCatalogueCategoryId()));
			catalogue.setCreateTime(new Date());
		} else {
			catalogue.setUpdateTime(new Date());
			//整体删除信息项
			List<CatalogueItem> items = catalogueItemService.selectList(new EntityWrapper<CatalogueItem>().eq(QueryField.RESOURCE_CATALOGUE_CODE, catalogue.getCatalogueCode()));
			if(items != null && !items.isEmpty()) {
				List<Long> idList = items.stream().map(CatalogueItem::getCatalogueItemId).collect(Collectors.toList());
				catalogueItemService.deleteBatchIds(idList);
			}
		}
		//插入或编辑资源目录
		this.insertOrUpdate(catalogue);
		
		//整体插入信息项
		List<CatalogueItem> items = catalogue.getItems();
		if(items != null && !items.isEmpty()) {
			for(CatalogueItem item : items) {
				item.setCatalogueCode(catalogue.getCatalogueCode());
				item.setCreateTime(new Date());
				item.setPublishDate(new Date());
			}
			catalogueItemService.insertBatch(items);
		}
	}

	@Override
	public Catalogue detail(String id) {
		//查询资源目录
		Catalogue rc = this.selectById(id);
		if(rc != null) {
			//查询目录名称
			CatalogueCategory catalogueInfo = catalogueCategoryService.selectById(rc.getCatalogueCategoryId());
			rc.setCatalogueName(catalogueInfo != null ? catalogueInfo.getCatalogueName() : ""); //设置目录名称
		}
		return rc;
	}

	@Override
	@Transactional
	public void audit(String[] ids) {
		
		// 状态：0=待审核，1=审核中，2=未通过，3=已发布
		List<Catalogue> rcInfos = this.selectList(
				new EntityWrapper<Catalogue>()
					.in(QueryField.CATALOGUE_CODE, ids)
				);
		if(rcInfos != null && !rcInfos.isEmpty()) {
			rcInfos.forEach(rcinfo -> rcinfo.setStatus(1));
			this.updateBatchById(rcInfos);
			
			List<ManageAuditEntity> maes = new ArrayList<>();
			for(Catalogue icInfo : rcInfos) {
				ManageAuditEntity mae = new ManageAuditEntity();
				mae.setCatalogueCode(icInfo.getCatalogueCode());
				mae.setType(1);
				mae.setEventId(2L);
				mae.setCreateUserId(null); //TOOD
				mae.setCreateTime(new Date());
				maes.add(mae);
			}
			manageAuditService.insertBatch(maes);
		}
		
	}

	@Override
	@Transactional
	public void delBatch(String[] ids) {
		List<Catalogue> infos = this.selectBatchIds(Arrays.asList(ids));
		if(infos != null && !infos.isEmpty()) {
			infos.forEach(info -> info.setDeleted(true));
			this.updateBatchById(infos);
		}
	}

	@Override
	@Transactional
	public List<CatalogueImportModel> importTempl(MultipartFile file) throws Exception {
		if (file == null) {
			return new ArrayList<>();
		}
		ExcelImportResult<CatalogueImportModel> excelImportResult = new ExcelImportResult<>(); //导入结果
		ImportParams params = new ImportParams();
		params.setTitleRows(1);
		params.setStartSheetIndex(1);
		List<CatalogueImportModel> list = ExcelImportUtil.importExcel(file.getInputStream(), CatalogueImportModel.class, params);
			
		params.setStartRows(1);
		params.setTitleRows(0);
		List<CatalogueImportModel> list2 = ExcelImportUtil.importExcel(file.getInputStream(), CatalogueImportModel.class, params);
		
		//合并2次导入到一个list中，因为导入模板中存在合并后的单元格，单次导入不能将全部数据导入，所以合并2次导入
		if(list != null && !list.isEmpty() && list2 != null && !list2.isEmpty()) {
			//合并2次导入数据
			list = com.wp.modules.catalogue.utils.ExcelImportUtil.combinList(list, list2);
			//校验导入的数据项
			excelImportResult = com.wp.modules.catalogue.utils.ExcelImportUtil.verify(list);
			if(excelImportResult.getList() != null && !excelImportResult.getList().isEmpty()) {
				//将校验通过的数据复制给Catalogue对象，并插入到数据库
				List<Catalogue> catalogs = com.wp.modules.catalogue.utils.ExcelImportUtil.copyCatalogEntity(excelImportResult.getList());
				//将校验通过的数据复制给CatalogueItem对象，并插入到数据库
				List<CatalogueItem> items = com.wp.modules.catalogue.utils.ExcelImportUtil.copyCatalogItemEntity(excelImportResult.getList());
				//插入到数据库
				if(catalogs != null && !catalogs.isEmpty()) {
					this.insertBatch(catalogs);
				}
				//插入到数据库
				if(items != null && !items.isEmpty()) {
					catalogueItemService.insertBatch(items);
				}
			}
		}
		//返回失败的结果
		return excelImportResult.getFailList();
	}

	@Override
	@Transactional
	public void recall(Catalogue catalogue) {
		catalogue.setStatus(0);
		this.updateById(catalogue);
		
		List<ManageAuditEntity> maes = new ArrayList<>();
		ManageAuditEntity mae = new ManageAuditEntity();
		mae.setCatalogueCode(catalogue.getCatalogueCode());
		mae.setType(1);
		mae.setEventId(6L);
		mae.setCreateUserId(null); //TOOD
		mae.setCreateTime(new Date());
		maes.add(mae);
		manageAuditService.insertBatch(maes);
		
	}

}
