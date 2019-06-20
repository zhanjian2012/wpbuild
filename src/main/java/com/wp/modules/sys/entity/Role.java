package com.wp.modules.sys.entity;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wp.common.BaseQuery;

@TableName("sys_role")
public class Role extends BaseQuery {
    /**   
	 * @fields serialVersionUID :  
	 */ 
	private static final long serialVersionUID = 1L;
	/**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 角色标识 程序中判断使用,如"admin"
     */
    @NotBlank(message = "角色标识不能为空")
    private String role;
    /**
     * 角色描述,UI界面显示使用
     */
    @NotBlank(message = "角色描述不能为空")
    private String description;
    /**
     * 拥有的资源
     */
    @NotBlank(message = "拥有的资源不能为空")
    private String resourceIds;
    /**
     * 拥有的资料列表
     */
    @TableField(exist=false)
    private String resourceNames;
    /**
     * 是否可用,如果不可用将不会添加给用户
     */
    private Boolean available = Boolean.FALSE;

    public Role() {
    }

    public Role(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getResourceNames() {
		return resourceNames;
	}

	public void setResourceNames(String resourceNames) {
		this.resourceNames = resourceNames;
	}

	public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

}
