package com.wp.modules.sys.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wp.common.PageResult;
import com.wp.modules.sys.entity.Log;
import com.wp.modules.sys.service.LogService;

/**
 * 
 * @Description： 日志
 * @author [ Wenfeng.Huang ] on [2018年8月31日上午10:39:37]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping
    @RequiresPermissions("log:view")
    public String page(Model model) {
        return "system/log";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("log:view")
    public PageResult<Log> list(Log log) {
        return logService.findByPage(log);
    }

}
