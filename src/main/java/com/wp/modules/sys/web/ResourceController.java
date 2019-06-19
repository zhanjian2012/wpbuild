package com.wp.modules.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String page(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
        return "system/resource";
    }

    @ResponseBody
    @PostMapping("/create")
    public Result<?> create(Resource resource) {
        resourceService.create(resource);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/update")
    public Result<?> update(Resource resource) {
        resourceService.update(resource);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result<?> delete(@RequestParam("id") Long id) {
        resourceService.delete(id);
        return Result.success();
    }

}
