package com.wp.modules.sys.service;

import com.wp.modules.sys.entity.SysOrgEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy
 * @description 组织
 * @time 2019/5/27 16:38
 */
public interface SysOrgService {

	boolean saveOrg(SysOrgEntity org);

	SysOrgEntity getOrg(Long orgId);

	boolean updateOrg(SysOrgEntity org);

	List<Long> listOrgChildren(Long parentId);

	public List<Long> getAllOrgChildren(Long parentId);

	public List<SysOrgEntity> getOrgListRecursively();
}
