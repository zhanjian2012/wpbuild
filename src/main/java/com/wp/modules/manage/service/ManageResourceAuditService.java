package com.wp.modules.manage.service;

public interface ManageResourceAuditService {
    boolean addResourceAudit(Long resourceId, Long eventId, Long stateId, Long createUserId);
}
