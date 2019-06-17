package com.wp.modules.db_manager.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wp.modules.db_manager.entity.TDatabaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TDatabaseInfoMapper extends BaseMapper<TDatabaseInfo>
{
    //通过ids逻辑删除数据
    void deleteByIds(List<Long> ids);
}