package com.wp.modules.db_manager.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("t_tables_info")
public class TTablesInfo implements Serializable

{
    @TableId
    private Long tableId;

    /**
     *数据库名称
     */
    private String databaseName;

    /**
     *表名
     */
    private String tableName;

    /**
     *描述
     */
    private String tableDesc;

    /**
     *字段数量
     */
    private Integer columnCnt;

    /**
     *状态 1-正常 2-禁用 3-删除
     */
    private Integer isDelete;

    /**
     *创建人
     */
    private Long createUserId;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *修改人
     */
    private Long updateUserId;

    /**
     *修改时间
     */
    private Date updateTime;

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
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

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc == null ? null : tableDesc.trim();
    }

    public Integer getColumnCnt() {
        return columnCnt;
    }

    public void setColumnCnt(Integer columnCnt) {
        this.columnCnt = columnCnt;
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