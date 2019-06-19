package com.wp.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wp.common.PageResult;
import com.wp.modules.sys.entity.Log;

public interface LogService extends IService<Log> {

	void create(Log log) ;
	
	PageResult<Log> findByPage(Log log);
}
