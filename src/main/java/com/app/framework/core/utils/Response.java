package com.app.framework.core.utils;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by yangyijun on 2018/4/13.
 */
public class Response {
    @ApiModelProperty(value = "响应编码")
    public int code;
    @ApiModelProperty(value = "响应消息")
    public String msg;

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
