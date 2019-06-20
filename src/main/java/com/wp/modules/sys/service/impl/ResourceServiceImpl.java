package com.wp.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wp.modules.sys.entity.Resource;
import com.wp.modules.sys.mapper.ResourceMapper;
import com.wp.modules.sys.service.ResourceService;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> findAll() {
        return resourceMapper.selectList(new QueryWrapper<Resource>().orderByAsc("priority"));
    }

}
