package com.wp.modules.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wp.common.Result;
import com.wp.modules.sys.entity.Resource;
import com.wp.modules.sys.entity.Resource.ResourceType;
import com.wp.modules.sys.service.ResourceService;

/**
 * 
 * @Description： 资源管理
 * @author [ Wenfeng.Huang ] on [2018年8月24日下午5:30:57]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ModelAttribute("types")
    public ResourceType[] resourceTypes() {
        return ResourceType.values();
    }
    
    @GetMapping
    @RequiresPermissions("resource:view")
    public String page(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
        return "system/resource";
    }

    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("resource:save")
    public Result<?> save(Resource resource) {
    	Resource parent = resourceService.getById(resource.getParentId());
        resource.setParentIds(parent.makeSelfAsParentIds());
        resource.setAvailable(true);
        if (resource.getType() == ResourceType.MENU && StringUtils.isEmpty(resource.getUrl())) {
            resource.setUrl("#");
        }
    	resourceService.saveOrUpdate(resource);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("resource:delete")
    public Result<?> delete(@RequestParam("id") Long id) {
        resourceService.removeById(id);
        return Result.success();
    }

}
