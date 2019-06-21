package com.wp.modules.sys.web;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wp.common.PageResult;
import com.wp.common.Result;
import com.wp.common.ResultCodeEnum;
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

    @GetMapping
    public String page(Model model) {
        setCommonData(model);
        return "system/user";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageResult<User> list(User user) {
        return userService.findByPage(user);
    }

    @ResponseBody
    @PostMapping("/save")
    public Result<?> save(@Valid User user) {
        userService.saveOrUpdate(user);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result<?> delete(@RequestParam("id") Long[] ids, HttpServletRequest request) {
//        // 当前用户
//        String username = (String) SecurityUtils.getSubject().getPrincipal();
//        User user = userService.findByUsername(username);
//        boolean isSelf = Arrays.stream(ids).anyMatch(id -> id.equals(user.getId()));
//        if (isSelf) {
//            return Result.failure(ResultCodeEnum.FAILED_DEL_OWN);
//        }
        Arrays.asList(ids).forEach(id -> userService.removeById(id));
        return Result.success();
    }

    private void setCommonData(Model model) {
//        model.addAttribute("organizationList", organizationService.findAll());
//        model.addAttribute("roleList", roleService.findAll());
//        model.addAttribute("groupList",groupService.findAll());
    }

}
