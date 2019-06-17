package com.wp.modules.manage.service.impl;

import com.wp.modules.manage.entity.ManageAuditEntity;
import com.wp.modules.manage.service.ManageApplyAuditService;
import com.wp.modules.manage.service.ManageAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Murphy
 * @description 申请审核服务
 * @time 2019/5/29 15:04
 */
@Service("manageApplyAuditServiceImpl")
public class ManageApplyAuditServiceImpl implements ManageApplyAuditService {
    @Autowired
    private ManageAuditService manageAuditService;

    @Override
    public boolean addApplyAudit(Long applyId, Long eventId, Long stateId, Long createUserId) {
        ManageAuditEntity applyAudit = new ManageAuditEntity();
        applyAudit.setCreateTime(new Date());
        applyAudit.setCreateUserId(createUserId);
        applyAudit.setEventId(eventId);
        applyAudit.setStateId(stateId);
        applyAudit.setApplyId(applyId);
        applyAudit.setType(0);
        applyAudit.setDeleted(false);

        return manageAuditService.addManageAudit(applyAudit);
    }
}
