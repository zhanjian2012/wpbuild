package com.wp.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wp.modules.sys.entity.Resource;
import com.wp.modules.sys.entity.Resource.ResourceType;
import com.wp.modules.sys.mapper.ResourceMapper;
import com.wp.modules.sys.service.ResourceService;
import com.wp.modules.sys.service.UserService;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Resource> findAll() {
        return resourceMapper.selectList(new QueryWrapper<Resource>().orderByAsc("priority"));
    }

	@Override
	public List<Resource> getMenus(Set<String> permissions) {
//		List<Resource> list = findAll();
		List<Resource> list = resourceMapper.selectList(new QueryWrapper<Resource>().eq("type", ResourceType.MENU).orderByAsc("priority"));
		if(list == null || list.isEmpty()) {
			return Collections.emptyList();
		}
		List<Resource> menus = new ArrayList<>();
		list.forEach(r -> {
			if (r.isRootNode()) {
				return;
            }
//			if(r.getType() != ResourceType.MENU) {
//				return;
//			}
			if(!hasPermission(permissions, r)) {
				return;
			}
			menus.add(r);
		});
		return menus;
	}
	
	@Override
	public String getMenusTreeDom() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
        Set<String> permissions = userService.findPermissions(username);
        List<Resource> menus = getMenus(permissions);
        StringBuilder dom = new StringBuilder();
		getMenuTree(menus, 1L, dom);
		return dom.toString();
	}

	private List<Resource> getMenuTree(List<Resource> source, Long pid, StringBuilder dom) {
        List<Resource> target = getChildResourceByPid(source, pid);
        target.forEach(res -> {
            dom.append("<li class='treeview'>");
            dom.append("<a href='" + res.getUrl() + "'>");
            dom.append("<i class='" + res.getIcon() + "'></i>");
            dom.append("<span>" + res.getName() + "</span>");
            if ("#".equals(res.getUrl())) {
                dom.append("<span class='pull-right-container'><i class='fa fa-angle-left pull-right'></i> </span>");
            }
            dom.append("</a>");
            dom.append("<ul class='treeview-menu'>");
            res.setChildren(getMenuTree(source, res.getId(), dom));
            dom.append("</ul>");
            dom.append("</li>");
        });
        return target;
    }

    private List<Resource> getChildResourceByPid(List<Resource> source, Long pId) {
        List<Resource> child = new ArrayList<>();
        source.forEach(res -> {
            if (pId.equals(res.getParentId()) && res.getType() == ResourceType.MENU) {
                child.add(res);
            }
        });
        return child;
    }

    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if (StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

}
