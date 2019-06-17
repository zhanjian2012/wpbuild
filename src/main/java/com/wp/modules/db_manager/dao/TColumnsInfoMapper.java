package com.wp.modules.db_manager.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wp.modules.db_manager.entity.TColumnsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TColumnsInfoMapper extends BaseMapper<TColumnsInfo>
{
    //批量删除
    void deleteByIds(List<Long> ids);
}