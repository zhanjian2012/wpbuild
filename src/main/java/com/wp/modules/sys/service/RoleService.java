package com.wp.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wp.common.PageResult;
import com.wp.modules.sys.entity.Role;

public interface RoleService extends IService<Role> {

    PageResult<Role> findByPage(Role role);
}
