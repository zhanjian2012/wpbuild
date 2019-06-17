package com.wp.modules.catalogue.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("资源目录对象")
@TableName("cata_catalogue")
public class Catalogue implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@TableId(type=IdType.INPUT)
	@ApiModelProperty("信息资源目录代码")
	private String catalogueCode; //信息资源目录代码（根据目录分类编码生成）

	@ApiModelProperty("目录分类ID")
    private String catalogueCategoryId; //目录分类ID（根据规则生成）

	@ApiModelProperty("信息资源目录名称")
    private String name; //信息资源目录名称

	@ApiModelProperty("资源目录类型")
    private Integer type; //资源目录类型（1：接口；2：文件；3：库表）

	@ApiModelProperty("信息资源目录提供方")
    private String offerDepartment; //信息资源目录提供方

	@ApiModelProperty("提供方内部部门")
    private String offerInnerDepartment; //提供方内部部门（导入时使用）

	@ApiModelProperty("信息资源目录提供方代码")
    private String orgId; //提供方部门ID（信息资源提目录供方代码）

	@ApiModelProperty("信息资源格式分类")
    private Integer resourceFormatType; //信息资源格式分类（1：电子文件；2：电子表格；3：数据库；4：图形图像；5：流媒体；6：自描述格式）

	@ApiModelProperty("信息资源格式类型")
    private Integer resourceFormatTypeItem; //信息资源格式类型（电子文件：OFD、wps、xml、 txt、doc、docx、html、pdf、ppt；电子表：et、xls、xlsx；数据库：Dm、KingbaseES、 access、dbf、dbase、sysbase、oracle、sqlserver、db2；图形图像：jpg、gif、bmp；流媒体：swf、rm、mpg）

	@ApiModelProperty("关联资源目录代码")
    private String relativeResourceCode; //关联资源目录代码

	@ApiModelProperty("基础信息资源分类")
    private String basicResourceType; //基础信息资源分类

	@ApiModelProperty("主题信息资源分类")
    private String themeResourceType; //主题信息资源分类

	@ApiModelProperty("共享类型")
    private Integer shareType; //共享类型（1：无条件共享；2：有条件共享；3：不予共享）

	@ApiModelProperty("共享方式")
    private Integer shareMode; //共享方式（1：邮件；2：拷盘；3：介质交换）

	@ApiModelProperty("共享条件")
    private String shareCondition; //共享条件

	@ApiModelProperty("开放类型")
    private Integer openType; //开放类型（0：不可对社会开放；1：对社会开放）

	@ApiModelProperty("负责人")
    private String personCharge; //负责人

	@ApiModelProperty("联系电话")
    private String phone; //联系电话

	@ApiModelProperty("信息资源目录摘要")
    private String resourceDesc; //信息资源目录摘要

	@ApiModelProperty("目录分类ID")
    private Integer status; //状态：0=待审核，1=审核中，2=未通过，3=已发布

	@ApiModelProperty("目录分类ID")
    private Boolean deleted; //是否删除

	@ApiModelProperty("目录分类ID")
    private Long createUserId;

	@ApiModelProperty("目录分类ID")
    private Date createTime;

	@ApiModelProperty("目录分类ID")
    private Long updateUserId;

	@ApiModelProperty("目录分类ID")
    private Date updateTime;

    @TableField(exist=false)
    private String catalogueName; //目录名称，详情中显示使用
    
    @TableField(exist=false)
    private List<CatalogueItem> items; //信息项

	public String getCatalogueCode() {
		return catalogueCode;
	}

	public void setCatalogueCode(String catalogueCode) {
		this.catalogueCode = catalogueCode;
	}

	public String getCatalogueCategoryId() {
		return catalogueCategoryId;
	}

	public void setCatalogueCategoryId(String catalogueCategoryId) {
		this.catalogueCategoryId = catalogueCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOfferDepartment() {
		return offerDepartment;
	}

	public void setOfferDepartment(String offerDepartment) {
		this.offerDepartment = offerDepartment;
	}

	public String getOfferInnerDepartment() {
		return offerInnerDepartment;
	}

	public void setOfferInnerDepartment(String offerInnerDepartment) {
		this.offerInnerDepartment = offerInnerDepartment;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getResourceFormatType() {
		return resourceFormatType;
	}

	public void setResourceFormatType(Integer resourceFormatType) {
		this.resourceFormatType = resourceFormatType;
	}

	public Integer getResourceFormatTypeItem() {
		return resourceFormatTypeItem;
	}

	public void setResourceFormatTypeItem(Integer resourceFormatTypeItem) {
		this.resourceFormatTypeItem = resourceFormatTypeItem;
	}

	public String getRelativeResourceCode() {
		return relativeResourceCode;
	}

	public void setRelativeResourceCode(String relativeResourceCode) {
		this.relativeResourceCode = relativeResourceCode;
	}

	public String getBasicResourceType() {
		return basicResourceType;
	}

	public void setBasicResourceType(String basicResourceType) {
		this.basicResourceType = basicResourceType;
	}

	public String getThemeResourceType() {
		return themeResourceType;
	}

	public void setThemeResourceType(String themeResourceType) {
		this.themeResourceType = themeResourceType;
	}

	public Integer getShareType() {
		return shareType;
	}

	public void setShareType(Integer shareType) {
		this.shareType = shareType;
	}

	public Integer getShareMode() {
		return shareMode;
	}

	public void setShareMode(Integer shareMode) {
		this.shareMode = shareMode;
	}

	public String getShareCondition() {
		return shareCondition;
	}

	public void setShareCondition(String shareCondition) {
		this.shareCondition = shareCondition;
	}

	public Integer getOpenType() {
		return openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	public String getPersonCharge() {
		return personCharge;
	}

	public void setPersonCharge(String personCharge) {
		this.personCharge = personCharge;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getResourceDesc() {
		return resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCatalogueName() {
		return catalogueName;
	}

	public void setCatalogueName(String catalogueName) {
		this.catalogueName = catalogueName;
	}

	public List<CatalogueItem> getItems() {
		return items;
	}

	public void setItems(List<CatalogueItem> items) {
		this.items = items;
	}
    
}