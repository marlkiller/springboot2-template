package com.bespin.wzu3.config.resp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 5602713946613894625L;
    private List<T> list = new ArrayList();
    private int pageNum;
    private int pageSize;
    private long total = 0L;

    public PageResult(List<T> list, long total, PageQuery pageQuery) {
        this.list = list;
        this.total = total;
        this.pageNum = pageQuery.getPageNum();
        this.pageSize = pageQuery.getPageSize();
    }

    public PageResult(List<T> list, long total, int pageNum, int pageSize) {
        this.list = list;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return (int)Math.ceil((double)this.getTotal() / (double)this.getPageSize());
    }

    public List<T> getList() {
        return this.list;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public long getTotal() {
        return this.total;
    }

    public void setList(final List<T> list) {
        this.list = list;
    }

    public void setPageNum(final int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(final long total) {
        this.total = total;
    }
}
