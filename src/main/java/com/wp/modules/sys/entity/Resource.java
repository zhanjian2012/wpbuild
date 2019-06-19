package com.wp.modules.sys.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wp.common.BaseQuery;

@TableName("sys_resource")
public class Resource extends BaseQuery {

	private static final long serialVersionUID = 1L;

	/**
     * 编号
     */
    @TableId
    private Long id;

    @NotBlank(message = "资源名称不能为空")
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源类型   0：目录   1：菜单   2：按钮
     */
    private Integer type; 
    /**
     * 资源路径
     */
    private String url;
    /**
     * 权限字符串
     */
    private String permission;
    /**
     * 父编号
     */
    @NotNull(message = "父编号不能为空")
    private Long parentId;
    /**
     * 父编号列表
     */
    private String parentIds;
    
    /**
     * 是否可用
     */
    private Boolean available = Boolean.FALSE;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Long priority;

    /**
     * 叶子节点
     */
    private Boolean leaf = Boolean.FALSE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public boolean isRootNode() {
        return parentId == 0;
    }

    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }
    
    public String getTypeName() {
    	return type == 1 ? "菜单" : "按钮";
    }
}
