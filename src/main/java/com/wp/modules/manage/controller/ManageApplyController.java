package com.wp.modules.manage.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.wp.common.utils.JsonResult;
import com.wp.modules.manage.entity.ManageApplyEntity;
import com.wp.modules.manage.service.ManageApplyService;
import com.wp.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.Map;

/**
 * @author Murphy
 * @description 已发布资源申请
 * @time 2019/5/27 17:27
 */
@Api(description = "资源申请api")
@RestController
@RequestMapping("/manage/apply")
public class ManageApplyController extends AbstractController {
    @Autowired
    private ManageApplyService manageApplyService;

    @ApiOperation("新增资源申请")
    @PostMapping("/add")
    public JsonResult addManageApply(@RequestBody ManageApplyEntity manageApplyEntity) {
        manageApplyEntity.setCreateUserId(this.getUserId());
        manageApplyEntity.setCreateTime(new Date());

        boolean success = manageApplyService.addManageApply(manageApplyEntity);
        if (success) {
            return JsonResult.success("", "申请成功");
        }

        return JsonResult.fail("", "申请失败");
    }

    @ApiOperation("更新资源申请")
    @PostMapping("/update")
    public JsonResult updateManageApply(@RequestBody ManageApplyEntity manageApplyEntity) {
        manageApplyEntity.setUpdateUserId(this.getUserId());
        manageApplyEntity.setUpdateTime(new Date());

        boolean success = manageApplyService.updateManageApply(manageApplyEntity);
        if (success) {
            return JsonResult.success("", "更新成功");
        }

        return JsonResult.fail("", "更新失败");
    }

    @ApiOperation("终止资源申请")
    @PostMapping("/suspend")
    public JsonResult suspendManageApply(@RequestBody Long[] applyIds) {
        boolean success = manageApplyService.suspendManageApply(applyIds);
        if (success) {
            return JsonResult.success("", "终止成功");
        }

        return JsonResult.fail("", "终止失败");
    }

    @ApiOperation("删除资源申请")
    @PostMapping("/delete")
    public JsonResult deleteManageApplyBatch(@ApiIgnore @RequestBody Long[] applyIds) {
        boolean success = manageApplyService.deleteManageApplyBatch(applyIds);
        if (success) {
            return JsonResult.success("", "删除成功");
        }

        return JsonResult.fail("", "删除失败");
    }

    @ApiOperation("获取资源申请")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "createUserId", value = "申请者ID"),
            @ApiImplicitParam(name = "state", value = "资源状态", example = "0=待审核，1=审核中，2=未通过，3=已发布"),
            @ApiImplicitParam(name = "applyDepartment", value = "申请方"),
            @ApiImplicitParam(name = "departName", value = "提供方"),
            @ApiImplicitParam(name = "resourceName", value = "资源名称"),
            @ApiImplicitParam(name = "createStartTime", value = "申请时间起始"),
            @ApiImplicitParam(name = "createEndTime", value = "申请时间截止"),
            @ApiImplicitParam(name = "pageNumber", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "数量", required = true)
    })
    public JsonResult getManageApplyList(@RequestBody Map<String, Object> params){
        Page<ManageApplyEntity> page = manageApplyService.getManageApply(params);

        return JsonResult.success(page, "获取成功");
    }


}
