package com.wp.modules.db_manager.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wp.modules.db_manager.entity.TTablesInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TTablesInfoMapper extends BaseMapper<TTablesInfo>
{
    //根据id批量删除
    void deleteByIds(List<Long> ids);

    //根据数据库名字查询表数量，并每次增加一个返回
    Integer selectCountDb(@Param("dbName") String dbName);
}