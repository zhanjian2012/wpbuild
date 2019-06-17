package com.wp.modules.resource_register.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wp.modules.resource_register.entity.TResourcesInfoSub;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TResourcesInfoSubMapper extends BaseMapper<TResourcesInfoSub>
{
    int deleteByPrimaryKey(Long subItemId);

    int insertSelective(TResourcesInfoSub record);

    TResourcesInfoSub selectByPrimaryKey(Long subItemId);

    int updateByPrimaryKeySelective(TResourcesInfoSub record);

}