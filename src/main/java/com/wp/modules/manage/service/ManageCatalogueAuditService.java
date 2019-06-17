package com.wp.modules.manage.service;

public interface ManageCatalogueAuditService {
    boolean addCatalogueAudit(String catalogueCode, Long eventId, Long stateId, Long createUserId);
}
