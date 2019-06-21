package com.wp.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wp.modules.sys.entity.Organization;
import com.wp.modules.sys.mapper.OrganizationMapper;
import com.wp.modules.sys.service.OrganizationService;

@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization>  implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    @Transactional
    public void move(Organization source, Organization target) {
//        organizationMapper.updateById(target);
//        organizationMapper.updateSalefParentIds(source.makeSelfAsParentIds());
    }

	@Override
	public List<Organization> findAll() {
		return organizationMapper.selectList(new QueryWrapper<Organization>().orderByAsc("priority"));
	}
}
