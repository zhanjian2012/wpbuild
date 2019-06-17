package com.wp.modules.catalogue.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("目录分类对象")
@TableName("cata_catalogue_category")
public class CatalogueCategory implements Serializable {
	
	@ApiModelProperty("目录分类ID")
	@TableId(type=IdType.INPUT)
    private String catalogueId;

	@ApiModelProperty("目录分类名称")
    private String catalogueName;

	@ApiModelProperty("上级目录分类ID")
    private String parentCatalogueId;

	@ApiModelProperty("备注")
    private String remark;

	@ApiModelProperty("排序")
    private Integer orderNum;

	@ApiModelProperty("状态 1-上线 2-下线")
    private Integer status = 1;

	@ApiModelProperty("创建人")
    private Long createUserId;

	@ApiModelProperty("创建时间")
    private Date createTime = new Date();

	@ApiModelProperty("修改人")
    private Long updateUserId;

	@ApiModelProperty("修改时间")
    private Date updateTime;
    
    @TableField(exist=false)
    private boolean canOffline = true;  //是否可以下线
    
    @TableField(exist=false)
    private List<CatalogueCategory> children; //拼装树形结构

	public boolean isCanOffline() {
		return canOffline;
	}

	public void setCanOffline(boolean canOffline) {
		this.canOffline = canOffline;
	}

	public List<CatalogueCategory> getChildren() {
		return children;
	}

	public void setChildren(List<CatalogueCategory> children) {
		this.children = children;
	}

	private static final long serialVersionUID = 1L;

    public String getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(String catalogueId) {
        this.catalogueId = StringUtils.isEmpty(catalogueId) ? null : catalogueId;
    }

    public String getCatalogueName() {
        return catalogueName;
    }

    public void setCatalogueName(String catalogueName) {
        this.catalogueName = StringUtils.isEmpty(catalogueName) ? null : catalogueName.trim();
    }

    public String getParentCatalogueId() {
        return parentCatalogueId;
    }

    public void setParentCatalogueId(String parentCatalogueId) {
        this.parentCatalogueId = StringUtils.isEmpty(parentCatalogueId) ? null : parentCatalogueId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}