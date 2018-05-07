package com.app.framework.auth.shiro;

import com.app.framework.auth.service.UserService;
import com.app.framework.core.utils.Status;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.atomic.AtomicInteger;

public class UserCredentialsMatcher implements CredentialsMatcher {
    @Autowired
    private UserService userService;

    private Cache<String, AtomicInteger> passwordRetryCache;

    @Value("${shiro.login-max-retry:5}")
    private int maxRetry;
    @Value("${shiro.login-retry-wait:600}")
    private int waitTime;

    public UserCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        AtomicInteger retryCnt = passwordRetryCache.get(username);
        if (retryCnt == null) {
            retryCnt = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCnt);
        }
        if (retryCnt.incrementAndGet() > maxRetry) {
            throw new ExcessiveAttemptsException(String.format("%s,%s, %s秒后重试", Status.ACCOUNT_TOO_MANY_LOGIN_ATTEMPT.msg(), maxRetry, waitTime));
        }
        String dbPassword = new String((char[]) info.getCredentials());
        String inpassword = new String((char[]) token.getCredentials());
        boolean login = userService.login(dbPassword, inpassword);
        if (login) {
            passwordRetryCache.remove(username);
        }
        return login;
    }
}
