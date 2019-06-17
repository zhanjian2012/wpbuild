package com.wp.modules.catalogue.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.wp.common.utils.PageUtils;
import com.wp.modules.catalogue.entity.CatalogueItem;

/**
 * @description: 目录编制信息项service接口
 * @author: zhanjian
 * @date: 2019年5月29日 下午6:15:50
 * @version: v1.0
 */
public interface CatalogueItemService extends IService<CatalogueItem> {

	/**
	 * @description: 根据资源目录代码查询信息项列表
	 * @param resourceCatalogueCode 资源目录编码
	 * @return List
	 * @throws
	 */
	List<CatalogueItem> queryListCatalogueCode(String resourceCatalogueCode);
	
	/**
	 * @description: 根据条件分页查询信息项接口
	 * @param params
	 * @return PageUtils
	 * @throws
	 */
	PageUtils queryPage(Map<String, Object> params);
}
