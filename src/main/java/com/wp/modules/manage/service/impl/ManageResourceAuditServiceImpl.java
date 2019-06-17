package com.wp.modules.manage.service.impl;

import com.wp.modules.manage.entity.ManageAuditEntity;
import com.wp.modules.manage.service.ManageAuditService;
import com.wp.modules.manage.service.ManageResourceAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Murphy
 * @description 资源审核服务
 * @time 2019/5/29 14:24
 */
@Service("manageResourceAuditServiceImpl")
public class ManageResourceAuditServiceImpl implements ManageResourceAuditService {
    @Autowired
    private ManageAuditService manageAuditService;

    @Override
    public boolean addResourceAudit(Long resourceId, Long eventId, Long stateId, Long createUserId) {
        ManageAuditEntity resourceAudit = new ManageAuditEntity();
        resourceAudit.setCreateTime(new Date());
        resourceAudit.setCreateUserId(createUserId);
        resourceAudit.setEventId(eventId);
        resourceAudit.setStateId(stateId);
        resourceAudit.setResourceId(resourceId);
        resourceAudit.setType(2);
        resourceAudit.setDeleted(false);

        return manageAuditService.insert(resourceAudit);
    }
}
