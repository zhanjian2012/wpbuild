package com.wp.modules.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wp.modules.manage.entity.ManageApplyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ManageApplyDao extends BaseMapper<ManageApplyEntity> {
    long suspendManageApplyBatch(Long[] manageApplyIds);
    long deleteManageApplyBatch(Long[] manageApplyIds);
    List<ManageApplyEntity> selectManageApplyEntities(@Param("params")Map<String, Object> params);
    long selectManageApplyEntityCount(@Param("params")Map<String, Object> params);
}
