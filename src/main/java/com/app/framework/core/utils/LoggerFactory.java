package com.app.framework.core.utils;


/**
 * Created by yangyijun on 2018/4/13.
 */
public final class LoggerFactory {
    public static Log getLogger(Class cls) {
        return new Log(cls);
    }
}
