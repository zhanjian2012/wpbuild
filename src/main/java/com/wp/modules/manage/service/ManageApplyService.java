package com.wp.modules.manage.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wp.modules.manage.entity.ManageApplyEntity;

import java.util.Map;

public interface ManageApplyService extends IService<ManageApplyEntity> {
    boolean addManageApply(ManageApplyEntity manageApplyEntity);
    boolean updateManageApply(ManageApplyEntity manageApplyEntity);
    boolean suspendManageApply(Long[] applyIds);
    boolean deleteManageApplyBatch(Long[] applyIds);
    Page<ManageApplyEntity> getManageApply(Map<String, Object> params);
}
