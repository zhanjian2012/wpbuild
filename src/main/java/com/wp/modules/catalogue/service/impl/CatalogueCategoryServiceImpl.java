package com.wp.modules.catalogue.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wp.common.utils.PageUtils;
import com.wp.common.utils.Query;
import com.wp.modules.catalogue.dao.CatalogueCategoryDao;
import com.wp.modules.catalogue.entity.Catalogue;
import com.wp.modules.catalogue.entity.CatalogueCategory;
import com.wp.modules.catalogue.service.CatalogueCategoryService;
import com.wp.modules.catalogue.service.CatalogueService;
import com.wp.modules.catalogue.utils.QueryField;

/**
 * @description: 目录分类service接口实现逻辑
 * @author: zhanjian
 * @date: 2019年5月29日 下午6:04:18
 * @version: v1.0
 */
@Service
public class CatalogueCategoryServiceImpl extends ServiceImpl<CatalogueCategoryDao, CatalogueCategory> implements CatalogueCategoryService{

	@Lazy
	@Autowired
	private CatalogueService catalogueService;
	
	/**
	 * @description: 根据父节点ID查询树形结构
	 * @param parentId
	 * @return List
	 * @throws
	 */
	@Override
    public List<CatalogueCategory> findTree(String parentId) {
		//根据父节点查询目录列表信息
		List<CatalogueCategory> list = this.selectList(
				new EntityWrapper<CatalogueCategory>().eq(QueryField.PARENT_CATALOGUE_ID, parentId).eq(QueryField.STATUS, 1));
		
		//进行交易，并递归查询树形结构
        if (list != null && !list.isEmpty()) {
        	for (CatalogueCategory CatalogueCategory : list) {
        		if (parentId.equals(CatalogueCategory.getParentCatalogueId())) {
	        		List<CatalogueCategory> children = findTree(CatalogueCategory.getCatalogueId());
	        		CatalogueCategory.setChildren(children);
        		}
            }
        }
        return list;
    }
	
	/**
	 /**
	 * @description: 根据参数分页查询目录分类
	 * @param params
	 * @return PageUtils
	 * @throws
	 * @see com.wp.modules.catalogue.service.CatalogueCategoryService#queryPage(java.util.Map)
	 */
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String catalogueName = (String)params.get("catalogueName"); //获取前台参数
		String parentCatalogueId = (String)params.get("parentCatalogueId"); //获取前台参数

		//根据条件分页查询
		Page<CatalogueCategory> page = this.selectPage(
				new Query<CatalogueCategory>(params).getPage(),
				new EntityWrapper<CatalogueCategory>()
					.like(StringUtils.isNotBlank(catalogueName), QueryField.CATALOGUE_NAME, catalogueName)
					.eq(StringUtils.isNotBlank(parentCatalogueId), QueryField.PARENT_CATALOGUE_ID, parentCatalogueId)
		);
		
		//如果记录不为空，则查询目录下面是否存在资源，如果存在，则此条目录不能进行下线操作
		if(page != null) {
			List<CatalogueCategory> infos = page.getRecords();
			if(infos != null && !infos.isEmpty()) {
				for(CatalogueCategory info : infos) {
					int count = catalogueService.selectCount(new EntityWrapper<Catalogue>().eq(QueryField.CATALOGUE_CATEGORY_ID, info.getCatalogueId()));
					if(count > 0) {
						info.setCanOffline(false);
					}
				}
			}
		}
		return new PageUtils(page);
	}

}
