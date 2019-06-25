package com.wp.modules.sys.service;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wp.modules.sys.entity.Resource;

public interface ResourceService extends IService<Resource> {

	List<Resource> findAll();
	
	 /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<Resource> getMenus(Set<String> permissions);
    
    String getMenusTreeDom();
}
