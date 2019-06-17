package com.wp.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Murphy
 * @description 字典
 * @time 2019/5/27 15:49
 */
@ApiModel
@TableName("sys_macro")
public class SysMacroEntity implements Serializable {
    @ApiModelProperty("字典ID")
    @TableId
    private Long macroId;

    @ApiModelProperty("父ID")
    @TableField
    private Long parentId;

    @ApiModelProperty("名称")
    @TableField
    private String name;

    @ApiModelProperty("值")
    @TableField
    private String value;

    @ApiModelProperty("状态(0:隐藏; 1:显示)")
    @TableField
    private Integer status;

    @ApiModelProperty("显示顺序")
    @TableField
    private Integer orderNumber;

    @ApiModelProperty("描述")
    @TableField
    private String description;

    @ApiModelProperty("创建时间")
    @TableField
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField
    private Date updateTime;

    public SysMacroEntity() {
    }

    public Long getMacroId() {
        return macroId;
    }

    public void setMacroId(Long macroId) {
        this.macroId = macroId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysMacroEntity{" +
                "macroId=" + macroId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", status=" + status +
                ", orderNumber=" + orderNumber +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
