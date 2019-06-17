package com.wp.modules.catalogue.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("资源目录信息项对象")
@TableName("cata_catalogue_item")
public class CatalogueItem implements Serializable {
    
	@TableId
	@ApiModelProperty("资源目录信息项ID")
	private Long catalogueItemId;

	@ApiModelProperty("资源目录代码")
    private String catalogueCode;

	@ApiModelProperty("信息项目名称")
    private String catalogueItemName;

	@ApiModelProperty("数据类型（C:字符型;N:数值型;Y:货币型;D:日期型;T:日期时间型;L:逻辑型;M:备注型;G:通用型;B:双精度型;I:整型;F:浮点型;）")
    private String type;

	@ApiModelProperty("数据长度")
    private Integer dataLength;

	@ApiModelProperty("发布日期")
    private Date publishDate;

	@ApiModelProperty("更新周期")
    private Integer updateCycle;

	@ApiModelProperty("创建人")
    private String createUserId;

	@ApiModelProperty("创建时间")
    private Date createTime = new Date();

	@ApiModelProperty("修改人")
    private String updateUserId;

	@ApiModelProperty("修改时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

	public Long getCatalogueItemId() {
		return catalogueItemId;
	}

	public void setCatalogueItemId(Long catalogueItemId) {
		this.catalogueItemId = catalogueItemId;
	}

	public String getCatalogueCode() {
		return catalogueCode;
	}

	public void setCatalogueCode(String catalogueCode) {
		this.catalogueCode = catalogueCode;
	}

	public String getCatalogueItemName() {
		return catalogueItemName;
	}

	public void setCatalogueItemName(String catalogueItemName) {
		this.catalogueItemName = catalogueItemName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDataLength() {
		return dataLength;
	}

	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getUpdateCycle() {
		return updateCycle;
	}

	public void setUpdateCycle(Integer updateCycle) {
		this.updateCycle = updateCycle;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}