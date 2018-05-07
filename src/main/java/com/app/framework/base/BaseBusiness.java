package com.app.framework.base;

import com.app.framework.auth.model.LoginUser;
import com.app.framework.core.utils.Log;
import com.app.framework.core.utils.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class BaseBusiness {
    protected Log log = LoggerFactory.getLogger(this.getClass());

    protected LoginUser getUser() {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            return (LoginUser) subject.getSession().getAttribute("loginuser");
        }
        return null;
    }

    protected String getUserName() {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            return (String) subject.getPrincipal();
        }
        return null;
    }
}
