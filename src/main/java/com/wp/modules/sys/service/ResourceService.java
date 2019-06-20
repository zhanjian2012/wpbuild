package com.wp.modules.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wp.modules.sys.entity.Resource;

public interface ResourceService extends IService<Resource> {

	List<Resource> findAll();
}
