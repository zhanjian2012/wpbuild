package com.wp.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wp.common.PageResult;
import com.wp.modules.sys.entity.User;

public interface UserService extends IService<User> {

    PageResult<User> findByPage(User user);

}