package com.wp.modules.sys.service;


import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wp.modules.sys.entity.Organization;

public interface OrganizationService extends IService<Organization> {

	List<Organization> findAll();
	
    void move(Organization source, Organization target);
}
