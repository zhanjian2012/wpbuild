package com.wp.modules.sys.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wp.common.Result;
import com.wp.modules.sys.entity.Organization;
import com.wp.modules.sys.service.OrganizationService;

/**
 * 
 * @Description： 功能描述
 * @author [ Wenfeng.Huang ] on [2018年8月24日下午5:31:08]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    @RequiresPermissions("organization:view")
    public String page(Model model) {
    	model.addAttribute("orgList", organizationService.findAll());
        return "system/organization";
    }

    @ResponseBody
    @PostMapping("{id}/load")
    public Result<?> load(@PathVariable Long id) {
        Organization organization = organizationService.getById(id);
        return Result.success(organization);
    }

    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("organization:save")
    public Result<?> save(@Valid Organization organization) {
        organizationService.saveOrUpdate(organization);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("organization:delete")
    public Result<?> delete(Long id) {
        organizationService.removeById(id);
        return Result.success();
    }

}
