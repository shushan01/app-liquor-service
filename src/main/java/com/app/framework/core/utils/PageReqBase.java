package com.app.framework.core.utils;

import com.app.framework.base.BaseObject;

public class PageReqBase extends BaseObject {
    private static final long serialVersionUID = 4085664248711896230L;
    private int pageSize;
    private int pageIndex;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
