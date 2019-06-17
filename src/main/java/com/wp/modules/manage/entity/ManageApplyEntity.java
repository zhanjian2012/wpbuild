package com.wp.modules.manage.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Murphy
 * @description 已发布资源申请
 * @time 2019/5/27 17:30
 */
@ApiModel("资源申请")
@TableName("manage_apply")
public class ManageApplyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("申请ID")
    @TableId
    private Long id;

    @ApiModelProperty("资源ID")
    @TableField
    private Long resourceId;

    @ApiModelProperty("申请单位")
    @TableField
    private String applyDepartment;

    @ApiModelProperty("联系人姓名")
    @TableField
    private String contactorName;

    @ApiModelProperty("联系人电话")
    @TableField
    private String contactorPhone;

    @ApiModelProperty("联系人邮箱")
    @TableField
    private String contactorEmail;

    @ApiModelProperty("使用系统")
    @TableField
    private String applySystem;

    @ApiModelProperty("需求名称")
    @TableField
    private String needsName;

    @ApiModelProperty("申请依据")
    @TableField
    private String applyCredit;

    @ApiModelProperty("应用场景")
    @TableField
    private String applyScene;

    @ApiModelProperty("调用频率")
    @TableField
    private String callFrequency;

    @ApiModelProperty("起始时间")
    @TableField
    private Date startTime;

    @ApiModelProperty("结束时间")
    @TableField
    private Date endTime;

    @ApiModelProperty("其他技术请求")
    @TableField
    private String note;

    @ApiModelProperty("负责人")
    @TableField
    private String userName;

    @ApiModelProperty("联系电话")
    @TableField
    private String userPhone;

    @ApiModelProperty("附件信息")
    @TableField
    private String appendix;

    @ApiModelProperty("拒绝原因")
    @TableField
    private String declineReason;

    @ApiModelProperty("0=待审核，1=审核中，2=未通过，3=已发布")
    @TableField
    private Integer state;

    @ApiModelProperty("已暂停")
    @TableField
    private Boolean suspend;

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

    @ApiModelProperty("资源名称")
    private String resourceName;

    @ApiModelProperty("提供方")
    private String departName;

    @ApiModelProperty("更新周期")
    private String updateCycle;

    @ApiModelProperty("共享类型")
    private String shareType;

    public ManageApplyEntity() {
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

    public String getApplyDepartment() {
        return applyDepartment;
    }

    public void setApplyDepartment(String applyDepartment) {
        this.applyDepartment = applyDepartment;
    }

    public String getContactorName() {
        return contactorName;
    }

    public void setContactorName(String contactorName) {
        this.contactorName = contactorName;
    }

    public String getContactorPhone() {
        return contactorPhone;
    }

    public void setContactorPhone(String contactorPhone) {
        this.contactorPhone = contactorPhone;
    }

    public String getContactorEmail() {
        return contactorEmail;
    }

    public void setContactorEmail(String contactorEmail) {
        this.contactorEmail = contactorEmail;
    }

    public String getApplySystem() {
        return applySystem;
    }

    public void setApplySystem(String applySystem) {
        this.applySystem = applySystem;
    }

    public String getNeedsName() {
        return needsName;
    }

    public void setNeedsName(String needsName) {
        this.needsName = needsName;
    }

    public String getApplyCredit() {
        return applyCredit;
    }

    public void setApplyCredit(String applyCredit) {
        this.applyCredit = applyCredit;
    }

    public String getApplyScene() {
        return applyScene;
    }

    public void setApplyScene(String applyScene) {
        this.applyScene = applyScene;
    }

    public String getCallFrequency() {
        return callFrequency;
    }

    public void setCallFrequency(String callFrequency) {
        this.callFrequency = callFrequency;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getAppendix() {
        return appendix;
    }

    public void setAppendix(String appendix) {
        this.appendix = appendix;
    }

    public String getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(String declineReason) {
        this.declineReason = declineReason;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Boolean getSuspend() {
        return suspend;
    }

    public void setSuspend(Boolean suspend) {
        this.suspend = suspend;
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

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getUpdateCycle() {
        return updateCycle;
    }

    public void setUpdateCycle(String updateCycle) {
        this.updateCycle = updateCycle;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    @Override
    public String toString() {
        return "ManageApplyEntity{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", applyDepartment='" + applyDepartment + '\'' +
                ", contactorName='" + contactorName + '\'' +
                ", contactorPhone='" + contactorPhone + '\'' +
                ", contactorEmail='" + contactorEmail + '\'' +
                ", applySystem='" + applySystem + '\'' +
                ", needsName='" + needsName + '\'' +
                ", applyCredit='" + applyCredit + '\'' +
                ", applyScene='" + applyScene + '\'' +
                ", callFrequency='" + callFrequency + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", note='" + note + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", appendix='" + appendix + '\'' +
                ", declineReason='" + declineReason + '\'' +
                ", state=" + state +
                ", suspend=" + suspend +
                ", deleted=" + deleted +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", updateUserId=" + updateUserId +
                ", updateTime=" + updateTime +
                ", resourceName='" + resourceName + '\'' +
                ", departName='" + departName + '\'' +
                ", updateCycle='" + updateCycle + '\'' +
                ", shareType='" + shareType + '\'' +
                '}';
    }
}
