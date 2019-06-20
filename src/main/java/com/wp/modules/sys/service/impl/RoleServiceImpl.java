package com.wp.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wp.common.PageResult;
import com.wp.modules.sys.entity.Resource;
import com.wp.modules.sys.entity.Role;
import com.wp.modules.sys.mapper.RoleMapper;
import com.wp.modules.sys.service.ResourceService;
import com.wp.modules.sys.service.RoleService;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>  implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceService resourceService;

    @Override
    public PageResult<Role> findByPage(Role role) {
    	QueryWrapper<Role> wrapper = new QueryWrapper<>();
    	wrapper.like(!StringUtils.isEmpty(role.getRole()), "role", role.getRole())
    		   .like(!StringUtils.isEmpty(role.getDescription()), "description", role.getDescription())
    		   .orderBy(!StringUtils.isEmpty(role.getSort()), role.isAsc(), role.getSort());
    	
    	IPage<Role> page = roleMapper.selectPage(new Page<>(role.getOffset(), role.getLimit()), wrapper);
    	if(!page.getRecords().isEmpty()) {
    		page.getRecords().forEach(r -> {
    			if(!StringUtils.isEmpty(r.getResourceIds())) {
    				StringBuilder s = new StringBuilder();
    		        for (String resourceId : r.getResourceIds().split(",")) {
    		            Resource resource = resourceService.getById(resourceId);
    		            if (resource != null) {
    		                s.append(resource.getName()).append(",");
    		            }
    		        }
    		        if (s.length() > 0) {
    		            s.deleteCharAt(s.length() - 1);
    		        }
    		        r.setResourceNames(s.toString());
    			}
    		});
    	}
    	return new PageResult<>(page);
    }

}
