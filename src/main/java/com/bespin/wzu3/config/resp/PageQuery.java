package com.bespin.wzu3.config.resp;

import java.io.Serializable;

public class PageQuery implements Serializable {
    private static final long serialVersionUID = -4108125395061211364L;
    private static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 50;
    private static final int MAX_PAGE_SIZE = 1000;
    private Integer pageNum = 1;
    private Integer pageSize = 50;

    public PageQuery() {
    }

    public PageQuery(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        if (null == this.pageNum || this.pageNum < 1) {
            this.pageNum = 1;
        }

        return this.pageNum;
    }

    public Integer getPageSize() {
        if (null == this.pageNum || this.pageSize < 1) {
            this.pageSize = 50;
        }

        if (this.pageSize > 1000) {
            this.pageSize = 1000;
        }

        return this.pageSize;
    }

    public String toString() {
        return "PageQuery(pageNum=" + this.getPageNum() + ", pageSize=" + this.getPageSize() + ")";
    }

    public void setPageNum(final Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }
}