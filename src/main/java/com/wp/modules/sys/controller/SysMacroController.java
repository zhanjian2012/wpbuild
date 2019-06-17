package com.wp.modules.sys.controller;

import com.wp.common.utils.JsonResult;
import com.wp.common.utils.R;
import com.wp.modules.sys.entity.SysMacroEntity;
import com.wp.modules.sys.service.SysMacroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Murphy
 * @description 系统字典
 * @time 2019/5/27 e15:06
 */
@Api(description = "系统字典api")
@RestController
@RequestMapping("/sys/macro")
public class SysMacroController extends AbstractController {
    @Autowired
    private SysMacroService sysMacroService;

    @ApiOperation("根据描述模糊查询字典")
    @GetMapping("/list")
    @ApiImplicitParam(name = "描述", value = "description")
    public JsonResult getMacroList(String description) {
        List<SysMacroEntity> macros = sysMacroService.getSysMacros(description);

        return JsonResult.success(macros, "");
    }

}
