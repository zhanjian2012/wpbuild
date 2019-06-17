package com.wp.modules.resource_register.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wp.modules.resource_register.entity.TResourcesInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 资源接口
 */
@Mapper
@Repository
public interface TResourcesInfoMapper extends BaseMapper<TResourcesInfo>
{

    //查询所有资源id和数据库名
    List<Map<String, Object>> queryIdsAndDbNames();

    int deleteByPrimaryKey(String resourceId);

    int insertSelective(TResourcesInfo record);

    TResourcesInfo selectByPrimaryKey(String resourceId);

    int updateByPrimaryKeySelective(TResourcesInfo record);

}