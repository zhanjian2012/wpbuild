package com.wp.modules.sys.service.impl;


import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wp.common.PageResult;
import com.wp.modules.sys.entity.Organization;
import com.wp.modules.sys.entity.Role;
import com.wp.modules.sys.entity.User;
import com.wp.modules.sys.mapper.UserMapper;
import com.wp.modules.sys.service.OrganizationService;
import com.wp.modules.sys.service.RoleService;
import com.wp.modules.sys.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private OrganizationService organizationService;

    @Override
    public PageResult<User> findByPage(User user) {
    	QueryWrapper<User> wrapper = new QueryWrapper<>();
    	wrapper.like(!StringUtils.isEmpty(user.getUsername()), "username", user.getUsername())
    		   .orderBy(!StringUtils.isEmpty(user.getSort()), user.isAsc(), user.getSort());
    	IPage<User> page = userMapper.selectPage(new Page<>(user.getOffset(), user.getLimit()), wrapper);
    	if(!page.getRecords().isEmpty()) {
    		page.getRecords().forEach(u -> {
    			if(!StringUtils.isEmpty(u.getOrganizationId())) {
    				Organization org = organizationService.getById(u.getOrganizationId());
    				u.setOrganizationName(org != null ? org.getName() : "");
    			}
    			if(!StringUtils.isEmpty(u.getRoleIds())) {
    				Collection<Role> roles = roleService.listByIds(Arrays.asList(u.getRoleIds().split(",")));
    				if(roles != null && !roles.isEmpty()) {
    					StringBuilder sb = new StringBuilder();
    					roles.forEach(role -> {
    						sb.append(role.getDescription()).append(",");
    					});
    					if (sb.length() > 0) {
    						sb.deleteCharAt(sb.length() - 1);
    						u.setRoleNames(sb.toString());
    					}
    				}
    			}
    		});
    	}
    	return new PageResult<>(page);
    }

}
