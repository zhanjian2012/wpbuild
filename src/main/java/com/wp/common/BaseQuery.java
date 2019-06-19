package com.wp.common;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 排序字段
     */
    @JsonIgnore
    @TableField(exist = false)
    private String sort = "id";
    
    /**
     * 升序、降序
     */
    @JsonIgnore
    @TableField(exist = false)
    private String order = "asc";
    
    /**
     * 升序、降序
     */
    @JsonIgnore
    @TableField(exist = false)
    private boolean isAsc = true;

    /**
     * 偏移量
     */
    @JsonIgnore
    @TableField(exist = false)
    private int offset = 0;
    /**
     * 条数
     */
    @JsonIgnore
    @TableField(exist = false)
    private int limit = 10;

    public String getSort() {
        return ColumnConvert.camelToUnderline(sort);
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

	public boolean isAsc() {
		return "asc".equals(order);
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}
    
}
