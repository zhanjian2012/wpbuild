package com.wp.modules.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wp.modules.manage.entity.ManageAuditEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ManageAuditDao extends BaseMapper<ManageAuditEntity> {
    List<ManageAuditEntity> selectManageAuditEntities(@Param("params")Map<String, Object> params);
    long selectManageAuditEntityCount(@Param("params")Map<String, Object> params);
}
