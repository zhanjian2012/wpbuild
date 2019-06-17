package com.wp.modules.resource_register.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 资源表
 */
@TableName("t_resources_info")
public class TResourcesInfo implements Serializable
{
    @TableId
    private Long resourceId;

    /**
     * 资源类型 (库表、接口、文件)
     */
    private String resourceType;

    /**
     * 数据库
     */
    private String databaseName;

    /**
     * 数据库表
     */
    private String tableName;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 更新周期
     */
    private String updateCycle;

    /**
     * 所属机构
     */
    private String departId;

    /**
     * 应用系统
     */
    private String applicationSystem;

    /**
     * 所属域
     */
    private String region;

    /**
     * 目录标识
     */
    private String resourceCatalogueCode;

    /**
     * 共享类型
     */
    private String shareType;

    /**
     * 共享条件
     */
    private String shareCondition;

    /**
     * 开放类型
     */
    private String openType;

    /**
     * 开放条件
     */
    private String openCondition;

    /**
     * 描述
     */
    private String resourceDesc;

    /**
     * 附件关联地址
     */
    private String attachmentAddr;

    /**
     * 服务中文名称
     */
    private String serviceChinese;

    /**
     * 服务英文名称
     */
    private String serviceEnglish;

    /**
     * 接入类型
     */
    private String accessType;

    /**
     * 来源系统
     */
    private String sourceSystem;

    /**
     * 服务摘要
     */
    private String serviceDesc;

    /**
     * 调用频率
     */
    private String frequency;

    /**
     * 超时时间
     */
    private String overTime;

    /**
     * 授权方式
     */
    private String grantType;

    /**
     * 是否允许省级代理
     */
    private String isProvinceAgent;

    /**
     * 在线提交服务申请
     */
    private String onlineSubmit;

    /**
     * 是否上传附件
     */
    private String isUpload;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 0=待审核，1=审核中，2=未通过，3=已发布
     */
    private Integer status;

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

    /**
     * 状态 1-正常 2-禁用 3-删除
     */
    private Integer isDelete;
    /**
     * 查询TResourcesInfoSub中数据
     */
    @TableField(exist=false)
    private List<TResourcesInfoSub> subs;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName == null ? null : databaseName.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getUpdateCycle() {
        return updateCycle;
    }

    public void setUpdateCycle(String updateCycle) {
        this.updateCycle = updateCycle == null ? null : updateCycle.trim();
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId == null ? null : departId.trim();
    }

    public String getApplicationSystem() {
        return applicationSystem;
    }

    public void setApplicationSystem(String applicationSystem) {
        this.applicationSystem = applicationSystem == null ? null : applicationSystem.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getResourceCatalogueCode() {
        return resourceCatalogueCode;
    }

    public void setResourceCatalogueCode(String resourceCatalogueCode) {
        this.resourceCatalogueCode = resourceCatalogueCode == null ? null : resourceCatalogueCode.trim();
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType == null ? null : shareType.trim();
    }

    public String getShareCondition() {
        return shareCondition;
    }

    public void setShareCondition(String shareCondition) {
        this.shareCondition = shareCondition == null ? null : shareCondition.trim();
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType == null ? null : openType.trim();
    }

    public String getOpenCondition() {
        return openCondition;
    }

    public void setOpenCondition(String openCondition) {
        this.openCondition = openCondition == null ? null : openCondition.trim();
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc == null ? null : resourceDesc.trim();
    }

    public String getAttachmentAddr() {
        return attachmentAddr;
    }

    public void setAttachmentAddr(String attachmentAddr) {
        this.attachmentAddr = attachmentAddr == null ? null : attachmentAddr.trim();
    }

    public String getServiceChinese() {
        return serviceChinese;
    }

    public void setServiceChinese(String serviceChinese) {
        this.serviceChinese = serviceChinese == null ? null : serviceChinese.trim();
    }

    public String getServiceEnglish() {
        return serviceEnglish;
    }

    public void setServiceEnglish(String serviceEnglish) {
        this.serviceEnglish = serviceEnglish == null ? null : serviceEnglish.trim();
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType == null ? null : accessType.trim();
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem == null ? null : sourceSystem.trim();
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc == null ? null : serviceDesc.trim();
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency == null ? null : frequency.trim();
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime == null ? null : overTime.trim();
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType == null ? null : grantType.trim();
    }

    public String getIsProvinceAgent() {
        return isProvinceAgent;
    }

    public void setIsProvinceAgent(String isProvinceAgent) {
        this.isProvinceAgent = isProvinceAgent == null ? null : isProvinceAgent.trim();
    }

    public String getOnlineSubmit() {
        return onlineSubmit;
    }

    public void setOnlineSubmit(String onlineSubmit) {
        this.onlineSubmit = onlineSubmit == null ? null : onlineSubmit.trim();
    }

    public String getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload == null ? null : isUpload.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

	public List<TResourcesInfoSub> getSubs() {
		return subs;
	}

	public void setSubs(List<TResourcesInfoSub> subs) {
		this.subs = subs;
	}

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
}