package com.wp.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    	
    	IPage<Log> page = logMapper.selectPage(new Page<>(1, 10), new QueryWrapper<Log>());
    	return new PageResult<Log>(page);
    	
        /*if (!StringUtils.isEmpty(log.getOrderBy())) {
            PageHelper.orderBy(log.getOrderBy());
        }

        Weekend<Log> example = Weekend.of(Log.class);
        WeekendCriteria<Log, Object> criteria = example.weekendCriteria();

        if (!StringUtils.isEmpty(log.getUsername())) {
            criteria.andLike(Log::getUsername, "%" + log.getUsername() + "%");
        }
        if (!StringUtils.isEmpty(log.getIp())) {
            criteria.andLike(Log::getIp, "%" + log.getIp() + "%");
        }
        if (!StringUtils.isEmpty(log.getReqMethod())) {
            criteria.andLike(Log::getReqMethod, "%" + log.getReqMethod() + "%");
        }
        if (!StringUtils.isEmpty(log.getExecMethod())) {
            criteria.andLike(Log::getExecMethod, "%" + log.getExecMethod() + "%");
        }
        if (!StringUtils.isEmpty(log.getExecDesc())) {
            criteria.andLike(Log::getExecDesc, "%" + log.getExecDesc() + "%");
        }
        if (!StringUtils.isEmpty(log.getStatus())) {
            criteria.andLike(Log::getStatus, "%" + log.getStatus() + "%");
        }
        if (!StringUtils.isEmpty(log.getStartDate()) && !StringUtils.isEmpty(log.getEndDate())) {
            criteria.andGreaterThanOrEqualTo(Log::getCreateTime, log.getStartDate()).andLessThanOrEqualTo(Log::getCreateTime, log.getEndDate());
        }

        PageResultSet<Log> resultSet = new PageResultSet<>();
        Page<Log> page = PageHelper.offsetPage(log.getOffset(), log.getLimit()).doSelectPage(()-> logMapper.selectByExample(example));

        resultSet.setRows(page.getResult());
        resultSet.setTotal(page.getTotal());
        return resultSet;*/
    }
}
