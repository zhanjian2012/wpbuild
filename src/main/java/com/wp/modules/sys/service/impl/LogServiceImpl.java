package com.wp.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wp.common.PageResult;
import com.wp.modules.sys.entity.Log;
import com.wp.modules.sys.mapper.LogMapper;
import com.wp.modules.sys.service.LogService;


@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    @Transactional
    public void create(Log log) {
        logMapper.insert(log);
    }

    @Override
    public PageResult<Log> findByPage(Log log) {
    	QueryWrapper<Log> wrapper = new QueryWrapper<>();
    	wrapper.like(!StringUtils.isEmpty(log.getUsername()), "username", log.getUsername())
    		   .like(!StringUtils.isEmpty(log.getIp()), "ip", log.getIp())
    		   .like(!StringUtils.isEmpty(log.getReqMethod()), "req_method", log.getReqMethod())
    		   .like(!StringUtils.isEmpty(log.getExecMethod()), "exec_method", log.getExecMethod())
    		   .like(!StringUtils.isEmpty(log.getExecDesc()), "exec_desc", log.getExecDesc())
    		   .like(!StringUtils.isEmpty(log.getStatus()), "status", log.getStatus())
    		   .ge(!StringUtils.isEmpty(log.getStartDate()), "create_time", log.getStartDate())
    		   .le(!StringUtils.isEmpty(log.getEndDate()), "create_time", log.getEndDate())
    		   .orderBy(!StringUtils.isEmpty(log.getSort()), log.isAsc(), log.getSort());
    	
    	IPage<Log> page = logMapper.selectPage(new Page<>(log.getOffset(), log.getLimit()), wrapper);
    	return new PageResult<>(page);
    }
}
