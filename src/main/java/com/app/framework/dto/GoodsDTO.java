package com.app.framework.dto;

import com.app.framework.core.utils.PageReqBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class GoodsDTO extends PageReqBase {
    private String Keyword;

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String keyword) {
        Keyword = keyword;
    }
}
