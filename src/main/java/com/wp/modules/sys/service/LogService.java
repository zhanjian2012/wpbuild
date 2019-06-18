package com.wp.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wp.common.Result;
import com.wp.modules.sys.entity.Log;

public interface LogService extends IService<Log> {

	void create(Log log) ;
	
	Result<IPage<Log>> findByPage(Log log);
}
