package com.app.framework.core.utils;

import java.util.List;

/**
 * Created by yangyijun on 2018/4/16.
 */
public class PageResult<T> {
    private int pageNo;
    private int pageSize;
    private long total;
    private List<T> data;

    public PageResult(int pageNo, int pageSize, long total, List<T> data) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
    }

    public PageResult() {
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
