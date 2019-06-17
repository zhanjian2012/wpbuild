package com.wp.modules.catalogue.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wp.common.utils.PageUtils;
import com.wp.common.utils.Query;
import com.wp.modules.catalogue.dao.ResourceCatalogueInfoItemDao;
import com.wp.modules.catalogue.entity.CatalogueItem;
import com.wp.modules.catalogue.service.CatalogueItemService;
import com.wp.modules.catalogue.utils.QueryField;

/**
 * @description: 目录编制信息项service接口具体逻辑
 * @author: zhanjian
 * @date: 2019年5月29日 下午6:15:50
 * @version: v1.0
 */
@Service
public class CatalogueItemServiceImpl extends ServiceImpl<ResourceCatalogueInfoItemDao, CatalogueItem> implements CatalogueItemService{

	/**
	 * @description: 根据资源目录代码查询信息项列表
	 * @param resourceCatalogueCode 资源目录编码
	 * @return List
	 * @throws
	 */
	@Override
	public List<CatalogueItem> queryListCatalogueCode(String resourceCatalogueCode) {
		return this.selectList(new EntityWrapper<CatalogueItem>().eq(QueryField.RESOURCE_CATALOGUE_CODE, resourceCatalogueCode));
	}

	/**
	 * @description: 根据条件分页查询信息项接口
	 * @param params resourceCatalogueCode 资源目录编码
	 * @return PageUtils
	 * @throws
	 */
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String resourceCatalogueCode = (String)params.get("resourceCatalogueCode"); //资源目录编码

		Page<CatalogueItem> page = this.selectPage(
				new Query<CatalogueItem>(params).getPage(),
				new EntityWrapper<CatalogueItem>()
					.eq(!StringUtils.isEmpty(resourceCatalogueCode), QueryField.RESOURCE_CATALOGUE_CODE, resourceCatalogueCode)
		);

		return new PageUtils(page);
	}
	

}
