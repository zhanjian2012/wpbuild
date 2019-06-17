package com.wp.modules.resource_register.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

/**
 *
 * 资源表子表
 */
@TableName("t_resources_info_sub")
public class TResourcesInfoSub
{
    @TableId
    private Long subItemId;

    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 子信息名称
     */
    private String subItemName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 长度
     */
    private Integer dataLength;

    /**
     * 是否主键
     */
    private String isPrimary;

    /**
     * 是否为空
     */
    private String isNull;

    /**
     * 服务有效地址
     */
    private String serviceAddr;

    /**
     * 调用示例
     */
    private String invocationExample;

    /**
     * 附件地址
     */
    private String attachmentAddr;

    /**
     * 状态码
     */
    private String interfStatus;

    /**
     * 技术支持单位
     */
    private String supportDepart;

    /**
     * 技术支持单位联系人
     */
    private String supportDepartPerson;

    /**
     * 技术支持单位电话
     */
    private String supportDepartPhone;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Long updateUserId;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Long getSubItemId() {
        return subItemId;
    }

    public void setSubItemId(Long subItemId) {
        this.subItemId = subItemId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getSubItemName() {
        return subItemName;
    }

    public void setSubItemName(String subItemName) {
        this.subItemName = subItemName == null ? null : subItemName.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType == null ? null : columnType.trim();
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public String getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary == null ? null : isPrimary.trim();
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull == null ? null : isNull.trim();
    }

    public String getServiceAddr() {
        return serviceAddr;
    }

    public void setServiceAddr(String serviceAddr) {
        this.serviceAddr = serviceAddr == null ? null : serviceAddr.trim();
    }

    public String getInvocationExample() {
        return invocationExample;
    }

    public void setInvocationExample(String invocationExample) {
        this.invocationExample = invocationExample == null ? null : invocationExample.trim();
    }

    public String getAttachmentAddr() {
        return attachmentAddr;
    }

    public void setAttachmentAddr(String attachmentAddr) {
        this.attachmentAddr = attachmentAddr == null ? null : attachmentAddr.trim();
    }

    public String getInterfStatus() {
        return interfStatus;
    }

    public void setInterfStatus(String interfStatus) {
        this.interfStatus = interfStatus == null ? null : interfStatus.trim();
    }

    public String getSupportDepart() {
        return supportDepart;
    }

    public void setSupportDepart(String supportDepart) {
        this.supportDepart = supportDepart == null ? null : supportDepart.trim();
    }

    public String getSupportDepartPerson() {
        return supportDepartPerson;
    }

    public void setSupportDepartPerson(String supportDepartPerson) {
        this.supportDepartPerson = supportDepartPerson == null ? null : supportDepartPerson.trim();
    }

    public String getSupportDepartPhone() {
        return supportDepartPhone;
    }

    public void setSupportDepartPhone(String supportDepartPhone) {
        this.supportDepartPhone = supportDepartPhone == null ? null : supportDepartPhone.trim();
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

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}