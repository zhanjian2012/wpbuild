package com.wp.modules.catalogue.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wp.modules.catalogue.entity.CatalogueCategory;

/**
 * @description: 目录分类数据库访问层
 * @author: zhanjian
 * @date: 2019年5月29日 下午6:11:43
 * @version: v1.0
 */
@Mapper
public interface CatalogueCategoryDao extends BaseMapper<CatalogueCategory> {

}
