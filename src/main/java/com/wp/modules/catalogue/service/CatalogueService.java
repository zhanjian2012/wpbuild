package com.wp.modules.catalogue.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.IService;
import com.wp.common.utils.PageUtils;
import com.wp.modules.catalogue.entity.Catalogue;
import com.wp.modules.catalogue.entity.CatalogueImportModel;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:34:11
 */
public interface CatalogueService extends IService<Catalogue> {

	Map<String, Integer> totals(String catalogueId);
	
	//分页，条件查询列表
	PageUtils queryPage(Map<String, Object> params);
	
	//保存
	void save(Catalogue catalogue);
	
	//资源目录详情
	Catalogue detail(String id);
	
	//目录编制提交审核
	void audit(String[] ids);
	
	//目录编制撤回
	void recall(Catalogue catalogue);
	
	//导入模板
	List<CatalogueImportModel> importTempl(MultipartFile file) throws Exception ;
	
	//批量删除
	void delBatch(String[] ids);
}
