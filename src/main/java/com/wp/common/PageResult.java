package com.wp.common;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 通用分页返回结果集
 *
 */
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 总数
     */
    private long total;
    /**
     * 返回的行数
     */
    private List<T> rows;

    public PageResult() {}
    
    public PageResult(IPage<T> page) {
    	this.total = page.getTotal();
    	this.rows = page.getRecords();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
