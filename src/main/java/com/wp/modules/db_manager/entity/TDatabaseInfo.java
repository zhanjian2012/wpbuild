package com.wp.modules.db_manager.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 数据库管理表
 */

@TableName("t_database_info")
public class TDatabaseInfo implements Serializable
{
    @TableId
    private Long databaseId;

    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * 数据库类型
     */
    private String databaseType;

    /**
     * 版本号
     */
    private String version;

    /**
     * 所属部门
     */
    private String departId;

    /**
     * 表数量
     */
    private Integer tablesCnt;

    /**
     * 运行环境
     */
    private String softWork;

    /**
     * 应用系统
     */
    private String applySystem;

    /**
     * 数据库地址
     */
    private String databaseAddr;

    /**
     * 端口
     */
    private String databasePort;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 状态 1-正常 2-禁用 3-删除
     */
    private Integer isDelete;

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
     * 电话
     */
    private String mobilePhone;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审核状态：0=待审核，1=审核中，2=未通过，3=已发布
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Long databaseId) {
        this.databaseId = databaseId;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName == null ? null : databaseName.trim();
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType == null ? null : databaseType.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId == null ? null : departId.trim();
    }

    public Integer getTablesCnt() {
        return tablesCnt;
    }

    public void setTablesCnt(Integer tablesCnt) {
        this.tablesCnt = tablesCnt;
    }

    public String getSoftWork() {
        return softWork;
    }

    public void setSoftWork(String softWork) {
        this.softWork = softWork == null ? null : softWork.trim();
    }

    public String getApplySystem() {
        return applySystem;
    }

    public void setApplySystem(String applySystem) {
        this.applySystem = applySystem == null ? null : applySystem.trim();
    }

    public String getDatabaseAddr() {
        return databaseAddr;
    }

    public void setDatabaseAddr(String databaseAddr) {
        this.databaseAddr = databaseAddr == null ? null : databaseAddr.trim();
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort == null ? null : databasePort.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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