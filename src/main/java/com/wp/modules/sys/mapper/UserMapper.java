package com.wp.modules.sys.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wp.modules.sys.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}