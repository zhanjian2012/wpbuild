package com.wp.modules.catalogue.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wp.common.utils.R;
import com.wp.common.validator.ValidatorUtils;
import com.wp.modules.catalogue.entity.Catalogue;
import com.wp.modules.catalogue.entity.CatalogueCategory;
import com.wp.modules.catalogue.service.CatalogueCategoryService;
import com.wp.modules.catalogue.service.CatalogueService;
import com.wp.modules.catalogue.utils.CatalogueCodeUtils;
import com.wp.modules.catalogue.utils.QueryField;
import com.wp.modules.sys.controller.AbstractController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description: 目录分类：子目录相关接口
 * @author: zhanjian
 * @date: 2019年5月29日 下午5:25:11
 * @version: v1.0
 */
@RestController
@RequestMapping("/catalog/category")
@Api(tags="目录分类：子目录相关接口", value="")
public class CatalogueCategoryController extends AbstractController {
	
	@Autowired
	private CatalogueCategoryService catalogueCategoryService;
	
	@Autowired
	private CatalogueService catalogueService;
	
	/**
	 * @description: 查询目录分类树形结构接口：左侧树，默认查询全部，状态status=1为正常的目录
	 * @return R
	 * @throws
	 */
	@GetMapping("/findTree")
	@ApiOperation("查询目录分类树形结构接口：左侧树")
	public R findTree(){
		return R.ok().put("tree", catalogueCategoryService.findTree("0"));
	}
	
	/**
	 * @description: 根据条件分页查询子目录
	 * @param params：{"catalogueName":"***", "parentCatalogueId":"***"}
	 * @return R
	 * @throws
	 */
	@GetMapping("/list")
	@ApiOperation("分页和条件查询子目录接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "catalogueName", value = "目录名称", paramType = "query"),
        @ApiImplicitParam(name = "parentCatalogueId", value = "父节点目录ID(可为空)", paramType = "query"),
        @ApiImplicitParam(name = "currPage", value = "当前页码(默认为1)", paramType = "query"),
        @ApiImplicitParam(name = "limit", value = "每页条数(默认为10)", paramType = "query")
	})
	public R list(@ApiIgnore @RequestParam Map<String, Object> params){
		return R.ok().put("page", catalogueCategoryService.queryPage(params));
	}
	
	/**
	 * @description: 子目录下线操作
	 * @param id
	 * @return R
	 * @throws
	 */
	@GetMapping("/offline/{id}")
	@ApiOperation("子目录下线接口")
	public R offline(@ApiParam(name="id", value="目录ID") @PathVariable("id") String id){
		CatalogueCategory cc = catalogueCategoryService.selectById(id);
		if(cc == null) {
			return R.error("数据异常");
		}
		int count = catalogueService.selectCount(new EntityWrapper<Catalogue>().eq(QueryField.CATALOGUE_CATEGORY_ID, id));
		if(count > 0) {
			return R.error("当前分类下存在资源目录，不能下线！");
		}
		cc.setStatus(2);
		catalogueCategoryService.updateById(cc);
		return R.ok();
	}
	
	/**
	 * @description: 子目录上线操作
	 * @param id
	 * @return R
	 * @throws
	 */
	@GetMapping("/online/{id}")
	@ApiOperation("子目录上线接口")
	public R online(@ApiParam(name="id", value="目录ID") @PathVariable("id") String id){
		CatalogueCategory cc = catalogueCategoryService.selectById(id);
		if(cc == null) {
			return R.error("数据异常");
		}
		cc.setStatus(2);
		catalogueCategoryService.updateById(cc);
		return R.ok();
	}
	
	/**
	 * @description: 子目录保存操作
	 * @param id
	 * @return R
	 * @throws
	 */
	@PostMapping("/save")
	@ApiOperation("子目录保存接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "catalogueName", value = "目录分类名称", paramType = "query"),
        @ApiImplicitParam(name = "remark", value = "备注", paramType = "query"),
        @ApiImplicitParam(name = "parentCatalogueId", value = "上级分类ID", paramType = "query"),
        @ApiImplicitParam(name = "orderNum", value = "排序", paramType = "query")
	})
	public R save(@ApiIgnore @RequestBody CatalogueCategory catalogueCategory){
		ValidatorUtils.validateEntity(catalogueCategory);
		catalogueCategory.setCatalogueId(CatalogueCodeUtils.getcatalogueId(catalogueCategory.getParentCatalogueId()));
		catalogueCategoryService.insertOrUpdateAllColumn(catalogueCategory);
		return R.ok();
	}
	
	/**
	 * @description: 目录详情接口操作
	 * @param id
	 * @return R
	 * @throws
	 */
	@GetMapping("/info/{id}")
	@ApiOperation("子目录详情接口")
	public R info(@ApiParam(name="id", value="目录ID") @PathVariable("id") String id){
		return R.ok().put("catalog", catalogueCategoryService.selectById(id));
	}
	
}
