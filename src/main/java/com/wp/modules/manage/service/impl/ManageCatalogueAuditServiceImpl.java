package com.wp.modules.manage.service.impl;

import com.wp.modules.manage.entity.ManageAuditEntity;
import com.wp.modules.manage.service.ManageAuditService;
import com.wp.modules.manage.service.ManageCatalogueAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Murphy
 * @description 目录审核服务
 * @time 2019/5/29 14:24
 */
@Service("manageCatalogueAuditServiceImpl")
public class ManageCatalogueAuditServiceImpl implements ManageCatalogueAuditService {
    @Autowired
    private ManageAuditService manageAuditService;

    @Override
    public boolean addCatalogueAudit(String catalogueCode, Long eventId, Long stateId, Long createUserId) {
        ManageAuditEntity catalogueAudit = new ManageAuditEntity();
        catalogueAudit.setCreateTime(new Date());
        catalogueAudit.setCreateUserId(createUserId);
        catalogueAudit.setEventId(eventId);
        catalogueAudit.setStateId(stateId);
        catalogueAudit.setCatalogueCode(catalogueCode);
        catalogueAudit.setType(1);
        catalogueAudit.setDeleted(false);

        return manageAuditService.insert(catalogueAudit);
    }
}
