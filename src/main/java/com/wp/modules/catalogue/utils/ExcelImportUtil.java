package com.wp.modules.catalogue.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.wp.common.utils.SysMacroUtils;
import com.wp.modules.catalogue.annoation.DictoryVerify;
import com.wp.modules.catalogue.annoation.RepeatVerify;
import com.wp.modules.catalogue.entity.Catalogue;
import com.wp.modules.catalogue.entity.CatalogueImportModel;
import com.wp.modules.catalogue.entity.CatalogueItem;
import com.wp.modules.catalogue.service.CatalogueService;
import com.wp.modules.sys.entity.SysMacroEntity;
import com.wp.modules.sys.service.SysMacroService;

import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;

/**
 * @description: 导入excel工具类
 * @author: zhanjian
 * @date: 2019年6月5日 下午4:43:04
 * @version: v1.0
 */
@Component
public class ExcelImportUtil {
	
	private static SysMacroService sysMacroService;
    
	private static CatalogueService catalogueService;
	
	/**
	 * @description: 注入静态服务
	 * @param: @param sysMacroService  
	 * @throws
	 */
    @Autowired
    private ExcelImportUtil(SysMacroService sysMacroService, CatalogueService catalogueService) {
    	ExcelImportUtil.sysMacroService = sysMacroService;
    	ExcelImportUtil.catalogueService = catalogueService;
    }
	
    /**
     * @description: 合并2次导入结果，因为excel模板中存在合并标题格式
     * @param list
     * @param list2
     * @return
     * @throws
     */
    public static List<CatalogueImportModel> combinList(List<CatalogueImportModel> list, List<CatalogueImportModel> list2) {
    	for(int i=0; i<list.size(); i++) {
			CatalogueImportModel cim = list.get(i);
			CatalogueImportModel cim2 = list2.get(i);
			cim.setName(cim2.getName());
			cim.setCatalogueCode(cim2.getCatalogueCode());
			cim.setOrgId(cim2.getOrgId());
			cim.setResourceDesc(cim2.getResourceDesc());
		}
    	return list;
    }
    
    /**
     * @description: 将CatalogueImportModel复制到Catalogue中
     * @param models
     * @return
     * @throws
     */
    public static List<Catalogue> copyCatalogEntity(List<CatalogueImportModel> models) {
    	List<Catalogue> catalogs = new ArrayList<>();
    	for(CatalogueImportModel cim : models) {
			Catalogue catalog = new Catalogue();
			catalog.setBasicResourceType(cim.getBasicResourceType());
			catalog.setCatalogueCategoryId(cim.getCatalogueCategoryId());
			catalog.setCatalogueCode(cim.getCatalogueCode());
			catalog.setName(cim.getName());
			catalog.setOfferDepartment(cim.getOfferDepartment());
			catalog.setOfferInnerDepartment(cim.getOfferInnerDepartment());
			catalog.setOpenType(SysMacroUtils.getIntValue(cim.getOpenType(), "48"));
			catalog.setCreateUserId(null);
			catalog.setCreateTime(new Date());
			catalog.setOrgId(cim.getOrgId());
			catalog.setPersonCharge(cim.getPersonCharge());
			catalog.setPhone(cim.getPhone());
			catalog.setResourceDesc(cim.getResourceDesc());
			catalog.setResourceFormatType(SysMacroUtils.getIntValue(cim.getResourceFormatType(), "5"));
			
			List<SysMacroEntity> macros = sysMacroService.getSysMacros(cim.getResourceFormatTypeItem());
			if(macros != null && !macros.isEmpty()) {
				catalog.setResourceFormatTypeItem(Integer.parseInt(macros.get(0).getValue()));
			}
			catalog.setShareCondition(cim.getShareCondition());
			catalog.setShareMode(SysMacroUtils.getIntValue(cim.getShareMode(), "44"));
			catalog.setShareType(SysMacroUtils.getIntValue(cim.getShareType(), "40"));
			catalog.setThemeResourceType(cim.getThemeResourceType());
			catalog.setStatus(0);
			catalogs.add(catalog);
		}
    	return catalogs;
    }
    
    /**
     * @throws Exception 
     * @description: 将CatalogueImportModel复制到Catalogue中
     * @param models
     * @return
     * @throws
     */
    public static List<CatalogueItem> copyCatalogItemEntity(List<CatalogueImportModel> models) throws Exception {
    	List<CatalogueItem> catalogItems = new ArrayList<>();
    	for(CatalogueImportModel cim : models) {
    		CatalogueItem item = new CatalogueItem();
			item.setCatalogueCode(cim.getCatalogueCode());
			item.setCatalogueItemName(cim.getItemName());
			item.setCreateTime(new Date());
			item.setCreateUserId(null);
			item.setDataLength(!StringUtils.isEmpty(cim.getDataLength()) ? Integer.parseInt(cim.getDataLength()) : null);
			if(!StringUtils.isEmpty(cim.getPublishDate())) {
				Date date = new SimpleDateFormat("yyyy年MM月dd日").parse(cim.getPublishDate());
				item.setPublishDate(date);
			}
			item.setType(SysMacroUtils.getValue(cim.getDataType(), "51"));
			item.setUpdateCycle(SysMacroUtils.getIntValue(cim.getUpdateCycle(), "63"));
			catalogItems.add(item);
		}
    	return catalogItems;
    }
    
	/**
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 * @description: 校验导入项
	 * @param catalogs
	 * @return
	 * @throws
	 */
	public static ExcelImportResult<CatalogueImportModel> verify(List<CatalogueImportModel> catalogs) throws Exception {
		ExcelImportResult<CatalogueImportModel> excelImportResult = new ExcelImportResult<>();
		List<CatalogueImportModel> failList = new ArrayList<>();
		List<CatalogueImportModel> successList = new ArrayList<>();
		
		StringBuilder ids = new StringBuilder();
		for(int i=0; i<catalogs.size(); i++) {
			CatalogueImportModel cata = catalogs.get(i);
			ids.append(cata.getCatalogueCode()).append(";"); //id校验
			StringBuilder errMsg = new StringBuilder(); //存放错误信息
			Field[] fields = cata.getClass().getDeclaredFields();
			for(Field field : fields) {
				Annotation[] annotations = field.getAnnotations();
				if(annotations != null && annotations.length > 0) {
					field.setAccessible(true);
					Object value = field.get(cata);
					for(Annotation annotation : annotations) {
						if(annotation instanceof NotNull) {
							verifyNull(value, field, errMsg);
						}
						if(annotation instanceof Max) {
							verifyLength(value, field, errMsg);
						}
						if(annotation instanceof DictoryVerify) {
							verifyDictory(value, field, errMsg);
						}
						if(annotation instanceof RepeatVerify) {
							verifyRepeat(value, field, errMsg, ids);
						}
					}
				}
			}
			if(!StringUtils.isEmpty(errMsg)) {
				cata.setErrorMsg(errMsg.toString());
				cata.setRowNum(i + 1);
				failList.add(cata);
			} else {
				successList.add(cata);
			}
		}
		excelImportResult.setFailList(failList);
		excelImportResult.setList(successList);
		if(failList != null && !failList.isEmpty()) {
			excelImportResult.setVerfiyFail(true);
		}
		return excelImportResult;
	}
	
	/**
	 * @description: 校验数据是否为空
	 * @return
	 * @throws
	 */
	private static void verifyNull(Object value, Field field, StringBuilder errMsg) {
		if(StringUtils.checkValNull(value)) {
			NotNull notNull = field.getAnnotation(NotNull.class);
			errMsg.append(notNull.message()).append("; ");
		}
	}
	
	/**
	 * @description: 校验数据长度
	 * @return
	 * @throws
	 */
	private static void verifyLength(Object value, Field field, StringBuilder errMsg) {
		if(StringUtils.checkValNotNull(value)) {
			Max max = field.getAnnotation(Max.class);
			if(value.toString().length() > max.value()) {
				errMsg.append(max.message()).append("; ");
			}
		}
	}
	
	/**
	 * @description: 检验字典项
	 * @return
	 * @throws
	 */
	private static void verifyDictory(Object value, Field field, StringBuilder errMsg) {
		if(StringUtils.checkValNotNull(value)) {
			DictoryVerify dictoryVerify = field.getAnnotation(DictoryVerify.class);
			if("itemType".equals(dictoryVerify.value())) {
				List<SysMacroEntity> macros = sysMacroService.getSysMacros(value.toString());
				if(macros == null || macros.isEmpty()) {
					errMsg.append(dictoryVerify.message()).append("; ");
				}
			} else {
				String val = SysMacroUtils.getValue(value.toString(), dictoryVerify.value()); //根据字典项获取到的真正值
				if(StringUtils.isEmpty(val)) {
					errMsg.append(dictoryVerify.message()).append("; ");
				}
			}
		}
	}
	
	/**
	 * @description: 检验字典项
	 * @return
	 * @throws
	 */
	private static void verifyRepeat(Object value, Field field, StringBuilder errMsg, StringBuilder ids) {
		if(StringUtils.checkValNotNull(value)) {
			RepeatVerify repeatVerify = field.getAnnotation(RepeatVerify.class);
			List<Catalogue> rcInfos = catalogueService.selectList(
					new EntityWrapper<Catalogue>()
						.eq(QueryField.CATALOGUE_CODE, value)
						.eq(QueryField.DELETED, false)
					);
			
			if(rcInfos != null && !rcInfos.isEmpty()) {
				errMsg.append(repeatVerify.databaseMessage()).append("; ");
			}
			if(!StringUtils.isEmpty(ids) && ids.toString().contains(value.toString())) {
				errMsg.append(repeatVerify.excelMessage()).append("; ");
			}
		}
	}
	
}
