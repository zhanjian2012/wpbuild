package com.wp.modules.catalogue.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.wp.common.utils.R;
import com.wp.modules.catalogue.entity.Catalogue;
import com.wp.modules.catalogue.entity.CatalogueCategory;
import com.wp.modules.catalogue.entity.CatalogueImportModel;
import com.wp.modules.catalogue.service.CatalogueCategoryService;
import com.wp.modules.catalogue.service.CatalogueService;
import com.wp.modules.catalogue.service.CatalogueItemService;
import com.wp.modules.resource_register.service.TResourcesInfoService;
import com.wp.modules.sys.controller.AbstractController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description: 
 * @author: zhanjian
 * @date: 2019年5月30日 上午11:36:50
 * @version: v1.0
 */
@RestController
@Api(tags="资源目录+目录编制接口")
@RequestMapping("/catalog")
public class CatalogueController extends AbstractController {
	
	@Autowired
	private CatalogueCategoryService catalogueCategoryService;
	@Autowired
	private CatalogueService catalogueService;
	@Autowired
	private CatalogueItemService resourceCatalogueInfoItemService;
	@Autowired
	private TResourcesInfoService tResourcesInfoService;
	
	/***************************************↓↓资源目录接口↓↓*************************************************/
	
	/**
	 * @description: 资源目录：信息资源总数接口
	 * @param catalogueId 目录分类ID
	 * @return R
	 * @throws
	 */
	@GetMapping("/totals/{catalogueId}")
	@ApiOperation("资源目录：信息资源总数接口")
	public R totals(@ApiParam(name="catalogueId", value="目录ID") @PathVariable("catalogueId") String catalogueId){
		return R.ok().put("totals", catalogueService.totals(catalogueId));
	}
	
	/**
	 * @description: 资源目录：分页条件查询信息资源列表接口
	 * @param catalogueId 目录分类ID
	 * @return R
	 * @throws
	 */
	@GetMapping("/list")
	@ApiOperation("资源目录：分页条件查询信息资源列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "资源目录名称", paramType = "query"),
        @ApiImplicitParam(name = "catalogueCode", value = "资源目录代码", paramType = "query"),
        @ApiImplicitParam(name = "type", value = "资源目录类型", paramType = "query"),
        @ApiImplicitParam(name = "offerDepartment", value = "提供方", paramType = "query"),
        @ApiImplicitParam(name = "status", value = "状态：0=待审核，1=审核中，2=未通过，3=已发布", paramType = "query"),
        @ApiImplicitParam(name = "currPage", value = "当前页码(默认为1)", paramType = "query"),
        @ApiImplicitParam(name = "limit", value = "每页条数(默认为10)", paramType = "query")
	})
	public R list(@ApiIgnore @RequestParam Map<String, Object> params){
		return R.ok().put("page", catalogueService.queryPage(params));
	}
	
	/**
	 * @description: 资源目录：资源目录详情接口
	 * @param id 资源目录ID
	 * @return R
	 * @throws
	 */
	@GetMapping("/detail/{id}")
	@ApiOperation("资源目录：资源目录详情接口")
	public R detail(@ApiParam(name="id", value="资源目录ID") @PathVariable("id") String id){
		return R.ok().put("catalog", catalogueService.detail(id));
	}
	
	/**
	 * @description: 资源目录：资源详情接口
	 * @param id 资源ID
	 * @return R
	 * @throws
	 */
	@GetMapping("/res/detail/{id}")
	@ApiOperation("资源目录：资源详情接口")
	public R resDetail(@ApiParam(name="id", value="资源ID") @PathVariable("id") Long id){
		return R.ok().put("resource", tResourcesInfoService.detail(id));
	}

	/***************************************↑↑资源目录接口↑*************************************************/
	

	/***************************************↓↓目录编制接口↓↓*************************************************/
	
	/**
	 * @description: 目录编制：目录编制详情接口
	 * @param id 资源ID
	 * @return R
	 * @throws
	 */
	@GetMapping("/info/{id}")
	@ApiOperation("目录编制：目录编制详情接口")
	public R info(@ApiParam(name="id", value="目录编制ID") @PathVariable("id") String id){
		Catalogue rc = catalogueService.selectById(id);
		if(rc != null) {
			rc.setItems(resourceCatalogueInfoItemService.queryListCatalogueCode(rc.getCatalogueCode())); //设置信息项
			CatalogueCategory catalogueInfo = catalogueCategoryService.selectById(rc.getCatalogueCategoryId());
			rc.setCatalogueName(catalogueInfo != null ? catalogueInfo.getCatalogueName() : ""); //设置目录名称
		}
		return R.ok().put("catalog", rc);
	}
	
	/**
	 * @description: 目录编制：保存（新增或编辑）目录编制接口
	 * @param resourceCatalogueInfo
	 * @return R
	 * @throws
	 */
	@PostMapping("/save")
	@ApiOperation("目录编制：保存（新增或编辑）目录编制接口")
	public R save(@ApiParam(value="资源目录对象") @RequestBody Catalogue catalogue){
		catalogueService.save(catalogue);
		return R.ok();
	}
	
	/**
	 * @description: 目录编制：导入模板接口
	 * @param resourceCatalogueInfo
	 * @return R
	 * @throws
	 */
	@PostMapping("/importTempl")
	@ApiOperation("目录编制：导入模板接口")
	public R importTempl(@ApiParam(name="file", value="文件模板") @RequestParam(value = "file") MultipartFile file){
		try {
			List<CatalogueImportModel> list = catalogueService.importTempl(file);
			return R.ok().put("data", list);
		} catch (Exception e) {
			return R.error(e.getMessage());
		}
	}
	
	/**
	 * @description: 目录编制：撤回审核接口
	 * @param id
	 * @return
	 * @throws
	 */
	@GetMapping("/recall/{id}")
	@ApiOperation("目录编制：撤回审核接口")
	public R audit(@ApiParam(name="id", value="目录编制ID") @PathVariable("id") String id){
		if(StringUtils.isEmpty(id)) {
			return R.error("参数不正确");
		}
		Catalogue catalogue = catalogueService.selectById(id);
		if(catalogue == null || catalogue.getStatus() != 1) {
			return R.error("参数不正确或者数据状态不正确");
		}
		catalogueService.recall(catalogue);
		return R.ok();
	}
	
	/**
	 * @description: 目录编制：批量删除接口
	 * @param ids
	 * @return R
	 * @throws
	 */
	@GetMapping("/delBatch")
	@ApiOperation("目录编制：批量删除接口")
	public R delBatch(@ApiParam(value="ID数组，例如：['1','2']") @RequestBody String[] ids){
		if(ids == null || ids.length == 0) {
			return R.error("参数不正确");
		}
		catalogueService.delBatch(ids);
		return R.ok();
	}
	
	/**
	 * @description: 目录编制：提交审核接口
	 * @param ids
	 * @return R
	 * @throws
	 */
	@GetMapping("/audit")
	@ApiOperation("目录编制：提交审核接口")
	public R audit(@ApiParam(name="ids", value="多个资源目录ID") @RequestBody String[] ids){
		if(ids == null || ids.length == 0) {
			return R.error("参数不正确");
		}
		catalogueService.audit(ids);
		return R.ok();
	}
	
	/***************************************↑↑目录编制接口↑*************************************************/
	
}
