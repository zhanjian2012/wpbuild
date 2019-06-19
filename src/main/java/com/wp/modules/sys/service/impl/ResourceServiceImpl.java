package com.wp.modules.sys.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    @Transactional
    public void create(Resource resource) {
//        Resource parent = findOne(resource.getParentId());
//        resource.setParentIds(parent.makeSelfAsParentIds());
//        resource.setAvailable(true);
//        if (resource.getType() == ResourceType.MENU) {
//            if (StringUtils.isEmpty(resource.getUrl())) {
//                resource.setUrl("#");
//            }
//        }
        resourceMapper.insert(resource);
    }

    @Override
    @Transactional
    public void update(Resource resource) {
        resourceMapper.updateById(resource);
    }

    @Override
    @Transactional
    public void delete(Long resourceId) {
        resourceMapper.deleteById(resourceId);
    }

    @Override
    public Resource findOne(Long resourceId) {
        return resourceMapper.selectById(resourceId);
    }

    @Override
    public List<Resource> findAll() {
        return resourceMapper.selectList(new QueryWrapper<Resource>().orderByAsc("priority"));
    }

	@Override
	public Set<String> findPermissions(Set<Long> resourceIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> findMenus(Set<String> permissions) {
		// TODO Auto-generated method stub
		return null;
	}

}
