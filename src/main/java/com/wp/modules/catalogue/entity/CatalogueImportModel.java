package com.wp.modules.catalogue.entity;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.wp.modules.catalogue.annoation.DictoryVerify;
import com.wp.modules.catalogue.annoation.RepeatVerify;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;

/**
 * 
 * @description: 导入目录编制实体类
 * @author: zhanjian
 * @date: 2019年6月3日 下午2:17:58
 * @version: v1.0
 */
public class CatalogueImportModel implements IExcelModel, IExcelDataModel, Serializable {
    
	private static final long serialVersionUID = 1L;

	@Excel(name = "类分类（必填项）")
	@NotNull(message = "类分类不能为空")
	private String code1;
	
	@Excel(name = "项分类（必填项）")
	@NotNull(message = "项分类不能为空")
	private String code2;
	
	@Excel(name = "目分类（必填项）")
	@NotNull(message = "目分类不能为空")
	private String code3;
	
	@Excel(name = "细目分类（必填项）")
	@NotNull(message = "细目分类不能为空")
	@Max(message = "细目分类最大长度[1, 50]", value = 50)
	private String catalogueCategoryId;

    @Excel(name = "信息资源目录名称（必填项）")
    @NotNull(message = "信息资源目录名称不能为空")
	@Max(message = "信息资源目录名称长度[1, 50]", value = 50)
	private String name; 
	
    @Excel(name = "信息资源目录代码（必填项）")
    @NotNull(message = "信息资源目录代码不能为空")
    @RepeatVerify(databaseMessage = "信息资源目录代码和数据库数据重复", excelMessage = "信息资源目录代码和excel数据重复")
	@Max(message = "信息资源目录代码长度[1, 50]", value = 50)
	private String catalogueCode; 

    @Excel(name = "信息资源目录提供方（必填项）")
    @NotNull(message = "信息资源目录提供方不能为空")
	@Max(message = "信息资源目录提供方长度[1, 50]", value = 50)
    private String offerDepartment; //信息资源目录提供方

    @Excel(name = "提供方内部部门（选填项）")
	@Max(message = "提供方内部部门长度[1, 50]", value = 50)
    private String offerInnerDepartment; //提供方内部部门（导入时使用）

    @Excel(name = "信息资源目录提供方代码（必填项）")
    @NotNull(message = "信息资源目录提供方代码不能为空")
	@Max(message = "信息资源目录提供方代码长度[1, 50]", value = 50)
    private String orgId; //提供方部门ID（信息资源提目录供方代码）
    
    @Excel(name = "信息资源目录摘要（必填项）")
    @NotNull(message = "信息资源目录摘要不能为空")
	@Max(message = "信息资源目录摘要长度[1, 2000]", value = 2000)
    private String resourceDesc; //信息资源目录摘要

    @Excel(name = "信息资源格式分类（必填项）")
    @NotNull(message = "信息资源格式分类不能为空")
	@Max(message = "信息资源格式分类长度[1, 11]", value = 11)
    @DictoryVerify(message = "信息资源格式分类格式不正确", value = "5")
    private String resourceFormatType; //信息资源格式分类（1：电子文件；2：电子表格；3：数据库；4：图形图像；5：流媒体；6：自描述格式）
    
    @Excel(name = "信息资源格式类型（必填项）")
    @NotNull(message = "信息资源格式类型不能为空")
	@Max(message = "信息资源格式类型长度[1, 11]", value = 11)
    @DictoryVerify(message = "信息资源格式类型格式不正确", value = "itemType")
    private String resourceFormatTypeItem; //信息资源格式类型（电子文件：OFD、wps、xml、 txt、doc、docx、html、pdf、ppt；电子表：et、xls、xlsx；数据库：Dm、KingbaseES、 access、dbf、dbase、sysbase、oracle、sqlserver、db2；图形图像：jpg、gif、bmp；流媒体：swf、rm、mpg）

    @Excel(name = "基础信息分类（选填项）")
	@Max(message = "基础信息分类长度[1, 50]", value = 50)
    private String basicResourceType; //基础信息资源分类

    @Excel(name = "主题信息分类（选填项）")
	@Max(message = "基础信息分类长度[1, 50]", value = 50)
    private String themeResourceType; //主题信息资源分类

    /**********************信息项*************************/
    @Excel(name = "信息项名称（必填项）")
    @NotNull(message = "信息项名称不能为空")
	@Max(message = "信息项名称长度[1, 50]", value = 50)
    private String itemName;
    
    @Excel(name = "数据类型（必填项）")
    @NotNull(message = "信息项数据类型不能为空")
	@Max(message = "信息项数据类型长度[1, 11]", value = 11)
    @DictoryVerify(message = "信息资源格式类型格式不正确", value = "51")
    private String dataType;

    @Excel(name = "数据长度（必填项）")
    @NotNull(message = "信息项数据长度不能为空")
	@Max(message = "信息项数据长度长度[1, 11]", value = 11)
    private String dataLength;

    @Excel(name = "更新周期（必填项）")
    @NotNull(message = "信息项更新周期不能为空")
	@Max(message = "信息项更新周期长度[1, 11]", value = 11)
    @DictoryVerify(message = "信息项更新周期格式不正确", value = "63")
    private String updateCycle;
    
    @Excel(name = "发布日期（必填项）")
    @NotNull(message = "信息项发布日期不能为空")
    private String publishDate;
    
    /**********************信息项*************************/
    
    @Excel(name = "共享类型（必填项）")
    @NotNull(message = "共享类型不能为空")
	@Max(message = "共享类型长度[1, 11]", value = 11)
    @DictoryVerify(message = "共享类型格式不正确", value = "40")
    private String shareType; //共享类型（1：无条件共享；2：有条件共享；3：不予共享）

    @Excel(name = "共享方式（必填项）")
    @NotNull(message = "共享方式不能为空")
	@Max(message = "共享方式长度[1, 11]", value = 11)
    @DictoryVerify(message = "共享方式格式不正确", value = "44")
    private String shareMode; //共享方式（1：邮件；2：拷盘；3：介质交换）

    @Excel(name = "共享条件（必填项）")
    @NotNull(message = "共享条件不能为空")
	@Max(message = "共享条件长度[1, 200]", value = 200)
    private String shareCondition; //共享条件

    @Excel(name = "是否向社会开放（必填项）")
    @NotNull(message = "是否向社会开放不能为空")
    @DictoryVerify(message = "是否向社会开放格式不正确", value = "48")
	@Max(message = "是否向社会开放长度[1, 11]", value = 11)
    private String openType; //开放类型（0：不可对社会开放；1：对社会开放）
    
    @Excel(name = "开放条件（必填项）")
    @NotNull(message = "开放条件不能为空")
	@Max(message = "开放条件长度[1, 200]", value = 200)
    private String openCondition; 

    @Excel(name = "负责人（选填项）")
	@Max(message = "负责人[0, 50]", value = 50)
    private String personCharge; //负责人

    @Excel(name = "联系电话（选填项）")
	@Max(message = "联系电话[0, 50]", value = 50)
    private String phone; //联系电话

	public String getCode1() {
		return code1;
	}

	public String getCode2() {
		return code2;
	}

	public String getCode3() {
		return code3;
	}

	public String getCatalogueCategoryId() {
		return catalogueCategoryId;
	}

	public String getName() {
		return name;
	}

	public String getCatalogueCode() {
		return catalogueCode;
	}

	public String getOfferDepartment() {
		return offerDepartment;
	}

	public String getOfferInnerDepartment() {
		return offerInnerDepartment;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getResourceDesc() {
		return resourceDesc;
	}

	public String getResourceFormatType() {
		return resourceFormatType;
	}

	public String getResourceFormatTypeItem() {
		return resourceFormatTypeItem;
	}

	public String getBasicResourceType() {
		return basicResourceType;
	}

	public String getThemeResourceType() {
		return themeResourceType;
	}

	public String getItemName() {
		return itemName;
	}

	public String getDataType() {
		return dataType;
	}

	public String getDataLength() {
		return dataLength;
	}

	public String getUpdateCycle() {
		return updateCycle;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public String getShareType() {
		return shareType;
	}

	public String getShareMode() {
		return shareMode;
	}

	public String getShareCondition() {
		return shareCondition;
	}

	public String getOpenType() {
		return openType;
	}

	public String getPersonCharge() {
		return personCharge;
	}

	public String getPhone() {
		return phone;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public void setCode3(String code3) {
		this.code3 = code3;
	}

	public void setCatalogueCategoryId(String catalogueCategoryId) {
		this.catalogueCategoryId = catalogueCategoryId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCatalogueCode(String catalogueCode) {
		this.catalogueCode = catalogueCode;
	}

	public void setOfferDepartment(String offerDepartment) {
		this.offerDepartment = offerDepartment;
	}

	public void setOfferInnerDepartment(String offerInnerDepartment) {
		this.offerInnerDepartment = offerInnerDepartment;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public void setResourceFormatType(String resourceFormatType) {
		this.resourceFormatType = resourceFormatType;
	}

	public void setResourceFormatTypeItem(String resourceFormatTypeItem) {
		this.resourceFormatTypeItem = resourceFormatTypeItem;
	}

	public void setBasicResourceType(String basicResourceType) {
		this.basicResourceType = basicResourceType;
	}

	public void setThemeResourceType(String themeResourceType) {
		this.themeResourceType = themeResourceType;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public void setUpdateCycle(String updateCycle) {
		this.updateCycle = updateCycle;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public void setShareMode(String shareMode) {
		this.shareMode = shareMode;
	}

	public void setShareCondition(String shareCondition) {
		this.shareCondition = shareCondition;
	}

	public String getOpenCondition() {
		return openCondition;
	}

	public void setOpenCondition(String openCondition) {
		this.openCondition = openCondition;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public void setPersonCharge(String personCharge) {
		this.personCharge = personCharge;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	private String errorMsg;
	
	private int rowNum;
	
	@Override
	public String getErrorMsg() {
		return errorMsg;
	}

	@Override
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public int getRowNum() {
		return rowNum;
	}

	@Override
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

}