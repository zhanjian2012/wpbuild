package com.wp.modules.db_manager.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("t_columns_info")
public class TColumnsInfo implements Serializable
{

    @TableId
    private Long colId;

    /**
     * 数据库id
     */
    private Long databaseId;

    /**
     * 表id
     */
    private Long tableId;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 字段中文名
     */
    private String columnChinese;

    /**
     * 字段类型 （C:字符型;N:数值型;Y:货币型;D:日期型;T:日期时间型;L:逻辑型;M:备注型;G:通用型;B:双精度型;I:整型;F:浮点型;）
     */
    private String columnType;

    /**
     * 类型长度
     */
    private Integer dataLength;

    /**
     * 是否主键 0-否 1-是
     */
    private String isPrimary;

    /**
     * 格式
     */
    private String format;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 共享类型(未知设置)
     */
    private String shareType;

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
     * 数据状态 1-正常 2-禁用 3-删除
     */
    private Integer isDelete;

    /**
     * 更新周期
     */
    private String updateCycle;

    public Long getColId() {
        return colId;
    }

    public void setColId(Long colId) {
        this.colId = colId;
    }

    public Long getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Long databaseId) {
        this.databaseId = databaseId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getColumnChinese() {
        return columnChinese;
    }

    public void setColumnChinese(String columnChinese) {
        this.columnChinese = columnChinese == null ? null : columnChinese.trim();
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
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

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType == null ? null : shareType.trim();
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getUpdateCycle() {
        return updateCycle;
    }

    public void setUpdateCycle(String updateCycle) {
        this.updateCycle = updateCycle == null ? null : updateCycle.trim();
    }
}