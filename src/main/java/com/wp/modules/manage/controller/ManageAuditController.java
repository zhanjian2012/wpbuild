package com.wp.modules.manage.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wp.common.utils.JsonResult;
import com.wp.modules.manage.entity.ManageAuditEntity;
import com.wp.modules.manage.service.ManageAuditService;
import com.wp.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @author Murphy
 * @description 审核流程
 * @time 2019/5/27 17:27
 */
@Api(description = "审核流程api")
@RestController
@RequestMapping("/manage/audit")
public class ManageAuditController extends AbstractController {
    @Autowired
    private ManageAuditService manageAuditService;

    @ApiOperation("新增审核流程")
    @PostMapping("/add")
    public JsonResult addManageAudit(@RequestBody ManageAuditEntity manageAuditEntity) {
        manageAuditEntity.setCreateUserId(this.getUserId());
        manageAuditEntity.setCreateTime(new Date());

        boolean success = manageAuditService.addManageAudit(manageAuditEntity);
        if (success) {
            return JsonResult.success("", "新增成功");
        }

        return JsonResult.fail("", "新增失败");
    }

    @ApiOperation("更新审核流程")
    @PostMapping("/update")
    public JsonResult updateManageAudit(@RequestBody ManageAuditEntity manageAuditEntity) {
        manageAuditEntity.setUpdateUserId(this.getUserId());
        manageAuditEntity.setCreateTime(new Date());

        boolean success = manageAuditService.updateManageAudit(manageAuditEntity);
        if (success) {
            return JsonResult.success("", "更新成功");
        }

        return JsonResult.fail("", "更新失败");
    }

    @ApiOperation("删除审核流程")
    @PostMapping("/delete")
    public JsonResult deleteManageAudit(@RequestParam("auditId") Long auditId) {
        boolean success = manageAuditService.deleteManageAudit(auditId);
        if (success) {
            return JsonResult.success("", "删除成功");
        }

        return JsonResult.fail("", "删除失败");
    }

    @ApiOperation("获取审核流程")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", required = true, example = "0=申请, 1=目录, 2=资源"),
            @ApiImplicitParam(name = "applyId", value = "资源申请ID"),
            @ApiImplicitParam(name = "catalogueCode", value = "目录编码"),
            @ApiImplicitParam(name = "resourceId", value = "资源ID"),
            @ApiImplicitParam(name = "pageNumber", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "数量")
    })
    public JsonResult getManageAuditList(@RequestBody Map<String, Object> params) {
        Page<ManageAuditEntity> page = manageAuditService.getManageAudit(params);

        return JsonResult.success(page, "获取成功");
    }

}
