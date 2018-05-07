package com.app.framework.core.utils;

import org.slf4j.*;

import java.text.MessageFormat;

/**
 * Created by yangyijun on 2018/4/13.
 */
public final class Log {
    private static Logger logger;

    public Log(Class cls) {
        logger = org.slf4j.LoggerFactory.getLogger(cls);
    }

    public void info(String pattern, Throwable t, Object... objs) {
        logger.info(MessageFormat.format(pattern, objs), t);
    }

    public void info(String pattern, Object... objs) {
        logger.info(pattern, objs);
    }

    public void info(String pattern, Throwable t) {
        logger.info(pattern, t);
    }

    public void warn(String pattern, Throwable t, Object... objs) {
        logger.warn(MessageFormat.format(pattern, objs), t);
    }

    public void warn(String pattern, Object... objs) {
        logger.warn(pattern, objs);
    }

    public void warn(String pattern, Throwable t) {
        logger.warn(pattern, t);
    }

    public void error(String pattern, Throwable t, Object... objs) {
        logger.error(MessageFormat.format(pattern, objs), t);
    }

    public void error(String pattern, Object... objs) {
        logger.error(pattern, objs);
    }

    public void error(String pattern, Throwable t) {
        logger.error(pattern, t);
    }

}
