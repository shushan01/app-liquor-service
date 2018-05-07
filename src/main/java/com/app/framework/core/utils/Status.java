package com.app.framework.core.utils;

public enum Status {

    SUCCESS(0, "success"),
    //shiro
    ACCOUNT_NOT_EXISTS(1, "账号不存在"),
    ACCOUNT_PASSWORD_ERROR(2, "密码错误"),
    ACCOUNT_NOT_LOGINED(3, "用户未通过认证"),
    ACCOUNT_INVALID_PERMISSION(4, "非法权限"),
    ACCOUNT_TOO_MANY_LOGIN_ATTEMPT(5, "超过最大登录失败次数"),
    ACCOUNT_CREDENTIAL_EXPIRED(6, "凭证过期"),
    ACCOUNT_LOCKED(7, "帐号被锁定"),
    ACOUNT_DISABLED(8, "帐号被禁用"),
    ACCOUNT_MISMATCH(9, "用户名或密码错误"),
    //mysql
    DUPLICATE_KEY(10, "重复的名字或主键值"),
    USER_ALREADY_EXISTS(11, "用户已存在");

    private int code;
    private String msg;

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String msg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static Status getByCode(int code) {
        for (Status s : Status.values()) {
            if (code == s.code()) {
                return s;
            }
        }
        return Status.SUCCESS;
    }
}
