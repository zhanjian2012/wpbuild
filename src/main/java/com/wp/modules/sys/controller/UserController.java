package com.wp.modules.sys.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wp.common.PageResult;
import com.wp.common.Result;
import com.wp.core.annoation.SystemLog;
import com.wp.core.shiro.PasswordHelper;
import com.wp.modules.sys.entity.User;
import com.wp.modules.sys.service.OrganizationService;
import com.wp.modules.sys.service.RoleService;
import com.wp.modules.sys.service.UserService;

/**
 * 
 * @Description： 用户管理
 * @author [ Wenfeng.Huang ] on [2018年8月24日下午5:30:44]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private PasswordHelper passwordHelper;

    @GetMapping
    @RequiresPermissions("user:view")
    public String page(Model model) {
    	 model.addAttribute("organizationList", organizationService.list());
         model.addAttribute("roleList", roleService.list());
        return "system/user";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("user:view")
    @SystemLog("用户管理：查询用户列表")
    public PageResult<User> list(User user) {
        return userService.findByPage(user);
    }

    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("user:save")
    @SystemLog("用户管理：新增/修改用户")
    public Result<?> save(User user) {
    	if(!StringUtils.isEmpty(user.getPassword())) {
    		passwordHelper.encryptPassword(user);
    	} else {
    		if(user.getId() != null) {
    			User u = userService.getById(user.getId());
    			user.setPassword(u.getPassword());
    			user.setSalt(u.getSalt());
    		} 
    	}
        userService.saveOrUpdate(user);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("user:delete")
    @SystemLog("用户管理：删除用户")
    public Result<?> delete(@RequestParam("id") Long[] ids, HttpServletRequest request) {
        Arrays.asList(ids).forEach(id -> userService.removeById(id));
        return Result.success();
    }

}
