package com.wp.modules.sys.controller;

import com.wp.common.utils.JsonResult;
import com.wp.modules.sys.entity.SysOrgEntity;
import com.wp.modules.sys.service.SysOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "组织部门", description = "组织部门")
@RestController
@RequestMapping("/sys/org")
public class SysOrgController extends AbstractController {
	@Autowired
	private SysOrgService sysOrgService;

	@ApiOperation("递归获取所有部门")
	@RequestMapping("/list")
	public JsonResult get() {
		List<SysOrgEntity> sysOrgEntities = sysOrgService.getOrgListRecursively();
		return JsonResult.success(sysOrgEntities, "获取成功");
	}
	
	@ApiOperation("新增部门")
	@RequestMapping("/add")
	public JsonResult save(@ApiIgnore @RequestBody SysOrgEntity org) {
		boolean success = sysOrgService.saveOrg(org);
		if (success) {
			return JsonResult.success("", "新增成功");
		} else {
			return JsonResult.fail("", "新增失败");
		}
	}

	@ApiOperation("查询部门详情")
	@RequestMapping("/detail")
	public JsonResult getDetailById(@RequestParam("orgId") Long orgId) {
		SysOrgEntity sysOrgEntity = sysOrgService.getOrg(orgId);
		return JsonResult.success(sysOrgEntity, "");
	}
	
	/**
	 * 修改部门
	 * @param org
	 * @return
	 */
	@ApiOperation("修改部门")
	@RequestMapping("/update")
	public JsonResult update(@RequestBody SysOrgEntity org) {
		boolean success = sysOrgService.updateOrg(org);
		if (success) {
			return JsonResult.success("", "修改成功");
		} else {
			return JsonResult.fail("", "修改失败");
		}
	}
	
}
