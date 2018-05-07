package com.app.framework.core.utils;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yangyijun on 2018/4/13.
 */
public final class ResponseData<T> extends Response {
    @ApiModelProperty(value = "响应数据")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
