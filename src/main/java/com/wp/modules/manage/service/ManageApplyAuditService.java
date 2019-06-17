package com.wp.modules.manage.service;

public interface ManageApplyAuditService {
    boolean addApplyAudit(Long applyId, Long eventId, Long stateId, Long createUserId);
}
