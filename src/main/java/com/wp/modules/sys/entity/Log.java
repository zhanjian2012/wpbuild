package com.wp.modules.sys.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wp.common.BaseQuery;

@TableName("sys_log")
public class Log extends BaseQuery {

	private static final long serialVersionUID = 1L;
	/**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户ip
     */
    private String ip;

    /**
     * 请求方法
     */
    private String reqMethod;

    /**
     * 执行方法
     */
    private String execMethod;

    /**
     * 响应时间
     */
    private Long execTime;

    /**
     * 描述
     */
    private String execDesc;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 请求URL
     */
    private String reqUri;

    /**
     * 参数
     */
    private String args;

    /**
     * 返回值
     */
    private String returnVal;
    
    @JsonIgnore
    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonIgnore
    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReqUri() {
        return reqUri;
    }

    public void setReqUri(String reqUri) {
        this.reqUri = reqUri;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getReturnVal() {
        return returnVal;
    }

    public void setReturnVal(String returnVal) {
        this.returnVal = returnVal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }

    public String getExecMethod() {
        return execMethod;
    }

    public void setExecMethod(String execMethod) {
        this.execMethod = execMethod;
    }

    public Long getExecTime() {
        return execTime;
    }

    public void setExecTime(Long execTime) {
        this.execTime = execTime;
    }

    public String getExecDesc() {
        return execDesc;
    }

    public void setExecDesc(String execDesc) {
        this.execDesc = execDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    
}