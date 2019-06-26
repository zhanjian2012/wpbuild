package com.wp.modules.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wp.modules.sys.service.ResourceService;

/**
 * 
 * @Description： 首页
 * @author [ Wenfeng.Huang ] on [2018年8月31日上午10:39:17]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("menuTree", resourceService.getMenusTreeDom());
        return "base/main";
    }

    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "base/unauthorized";
    }
    
}
