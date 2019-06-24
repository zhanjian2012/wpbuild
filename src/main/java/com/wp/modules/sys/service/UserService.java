package com.wp.modules.sys.service;

import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wp.common.PageResult;
import com.wp.modules.sys.entity.User;

public interface UserService extends IService<User> {

    PageResult<User> findByPage(User user);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);

}