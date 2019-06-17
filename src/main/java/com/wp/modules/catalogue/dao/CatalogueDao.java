package com.wp.modules.catalogue.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wp.modules.catalogue.entity.Catalogue;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:34:11
 */
@Mapper
public interface CatalogueDao extends BaseMapper<Catalogue> {

}
