package com.wp.modules.catalogue.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wp.common.utils.PageUtils;
import com.wp.common.utils.R;
import com.wp.modules.catalogue.service.CatalogueItemService;
import com.wp.modules.sys.controller.AbstractController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description: 目录编制信息项相关接口
 * @author: zhanjian
 * @date: 2019年5月29日 下午6:14:22
 * @version: v1.0
 */
@RestController
@Api(tags="资源目录信息项接口")
@RequestMapping("/rescatalog/item")
public class CatalogueItemController extends AbstractController {
	
	@Autowired
	private CatalogueItemService resourceCatalogueInfoItemService;
	
	/**
	 * @description: 根据条件分页查询信息项接口
	 * @param params
	 * @return R
	 * @throws
	 */
	@GetMapping("/list")
	@ApiOperation("根据条件分页查询信息项接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "resourceCatalogueCode", value = "资源目录编码(可为空)", paramType = "query"),
        @ApiImplicitParam(name = "currPage", value = "当前页码(默认为1)", paramType = "query"),
        @ApiImplicitParam(name = "limit", value = "每页条数(默认为10)", paramType = "query")
	})
	public R list(@ApiIgnore @RequestParam Map<String, Object> params){
		PageUtils page = resourceCatalogueInfoItemService.queryPage(params);
		return R.ok().put("page", page);
	}
	
}
