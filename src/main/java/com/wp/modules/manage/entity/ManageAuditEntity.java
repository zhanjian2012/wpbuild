package com.wp.modules.manage.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Murphy
 * @description 审核流程
 * @time 2019/5/27 17:30
 */
@ApiModel("审核流程")
@TableName("manage_audit")
public class ManageAuditEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableField
    private Long id;

    @ApiModelProperty("资源ID")
    @TableField
    private Long resourceId;

    @ApiModelProperty("目录编码")
    @TableField
    private String catalogueCode;

    @ApiModelProperty("申请ID")
    @TableField
    private Long applyId;

    @ApiModelProperty("类型(0=申请, 1=目录, 2=资源)")
    @TableField
    private Integer type;

    @ApiModelProperty("事件ID")
    @TableField
    private Long eventId;

    @ApiModelProperty("状态ID")
    @TableField
    private Long stateId;

    @ApiModelProperty("是否删除")
    @TableField
    private Boolean deleted;

    @ApiModelProperty("创建者ID")
    @TableField
    private Long createUserId;

    @ApiModelProperty("创建时间")
    @TableField
    private Date createTime;

    @ApiModelProperty("更新者ID")
    @TableField
    private Long updateUserId;

    @ApiModelProperty("更新时间")
    @TableField
    private Date updateTime;

    @ApiModelProperty("事件名称")
    private String eventName;

    @ApiModelProperty("状态名称")
    private String stateName;

    @ApiModelProperty("创建者名称")
    private String createUserName;

    public ManageAuditEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getCatalogueCode() {
        return catalogueCode;
    }

    public void setCatalogueCode(String catalogueCode) {
        this.catalogueCode = catalogueCode;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Override
    public String toString() {
        return "ManageAuditEntity{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", catalogueCode='" + catalogueCode + '\'' +
                ", applyId=" + applyId +
                ", type=" + type +
                ", eventId=" + eventId +
                ", stateId=" + stateId +
                ", deleted=" + deleted +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", updateUserId=" + updateUserId +
                ", updateTime=" + updateTime +
                ", eventName='" + eventName + '\'' +
                ", stateName='" + stateName + '\'' +
                ", createUserName='" + createUserName + '\'' +
                '}';
    }
}
