package com.wp.modules.manage.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wp.modules.manage.entity.ManageAuditEntity;

import java.util.Map;

public interface ManageAuditService extends IService<ManageAuditEntity> {
    boolean addManageAudit(ManageAuditEntity mangeAuditEntity);
    boolean updateManageAudit(ManageAuditEntity manageAuditEntity);
    boolean deleteManageAudit(Long manageAuditId);
    Page<ManageAuditEntity> getManageAudit(Map<String, Object> params);
}
