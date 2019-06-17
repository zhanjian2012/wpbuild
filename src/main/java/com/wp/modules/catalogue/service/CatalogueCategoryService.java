package com.wp.modules.catalogue.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.wp.common.utils.PageUtils;
import com.wp.modules.catalogue.entity.CatalogueCategory;

/**
 * @description: 目录分类service接口
 * @author: zhanjian
 * @date: 2019年5月29日 下午6:03:19
 * @version: v1.0
 */
public interface CatalogueCategoryService extends IService<CatalogueCategory> {

	/**
	 * @description: 根据父节点ID查询树形结构
	 * @param parentId
	 * @return List
	 * @throws
	 */
	List<CatalogueCategory> findTree(String parentId);
	
	/**
	 * @description: 根据参数分页查询
	 * @param params
	 * @return PageUtils
	 * @throws
	 */
	PageUtils queryPage(Map<String, Object> params);
}
