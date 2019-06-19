package com.wp.modules.sys.service;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wp.common.PageResult;
import com.wp.modules.sys.entity.User;

public interface UserService extends IService<User> {

    PageResult<User> findByPage(User user);

    void create(User user);

    void update(User user);

    void delete(Long userId);

    void changePassword(Long userId, String newPassword);

    User findOne(Long userId);

    List<User> findAll();

    public User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

}