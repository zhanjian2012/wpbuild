package com.wp.modules.catalogue.utils;

/**
 * @author zhanjian
 * service层中查询字段
 */
public class QueryField {
	
	private QueryField() {}
	
	//目录分类ID（根据规则生成）-对应数据库中：资源目录表（cata_catalogue）中字段
	public static final String CATALOGUE_CATEGORY_ID = "catalogue_category_id";
	
	//父目录ID-对应数据库中：资源目录表（t_catalogue_info）中字段
	public static final String PARENT_CATALOGUE_ID = "parent_catalogue_id";

	//资源目录ID-对应数据库中：资源目录表（t_resources_catalogue_info）中字段
	public static final String CATALOGUE_CODE = "catalogue_code";
	
	public static final String RESOURCE_CATALOGUE_CODE = "resource_catalogue_code";
	
	//资源目录类型-对应数据库中：资源目录表（t_resources_catalogue_info）中字段
	public static final String NAME = "name";
	
	public static final String CATALOGUE_NAME = "catalogue_name";
	
	//信息资源目录提供方-对应数据库中：资源目录表（t_resources_catalogue_info）中字段
	public static final String OFFER_DEPARTMENT = "offer_department";
	
	//资源目录名称-对应数据库中：资源目录表（t_resources_catalogue_info）中字段
	public static final String TYPE = "type";

	//目录ID-对应数据库中：目录表（t_catalogue_info）中字段
	public static final String CATALOGUE_ID = "catalogue_id";

	//状态字段-对应数据库中字段(状态：0=待审核，1=审核中，2=未通过，3=已发布)
	public static final String STATUS = "status";
	
	//是否删除字段-对应数据库中字段(是否删除（Y:已删；N:未删）)
	public static final String DELETED = "deleted"; 
	
	//数据库中字段值"N"
	public static final String N = "N"; 
	
}
