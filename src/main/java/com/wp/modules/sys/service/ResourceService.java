package com.wp.modules.sys.service;


import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wp.modules.sys.entity.Resource;


public interface ResourceService extends IService<Resource> {

    void create(Resource resource);

    void update(Resource resource);

    void delete(Long resourceId);

    Resource findOne(Long id);

    List<Resource> findAll();

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<Resource> findMenus(Set<String> permissions);

}
