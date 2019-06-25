package com.wp.modules.sys.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wp.common.PageResult;
import com.wp.common.Result;
import com.wp.modules.sys.entity.Role;
import com.wp.modules.sys.service.ResourceService;
import com.wp.modules.sys.service.RoleService;

/**
 * 
 * @Description： 角色管理
 * @author [ Wenfeng.Huang ] on [2018年8月24日下午5:30:52]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;


    @GetMapping
    @RequiresPermissions("role:view")
    public String page(Model model) {
    	model.addAttribute("resourceList", resourceService.list());
        return "system/role";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("role:view")
    public PageResult<Role> list(Role role) {
        return roleService.findByPage(role);
    }

    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("role:save")
    public Result<?> save(@Valid Role role) {
        roleService.saveOrUpdate(role);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("role:delete")
    public Result<?> delete(@RequestParam("id") Long[] ids) {
        Arrays.asList(ids).forEach(id-> roleService.removeById(id));
        return Result.success();
    }

}
