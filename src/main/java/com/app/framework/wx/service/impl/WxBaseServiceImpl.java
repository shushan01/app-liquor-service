package com.app.framework.wx.service.impl;

import com.app.framework.core.utils.HttpClientUtils;
import com.app.framework.wx.model.AccessToken;
import com.app.framework.wx.model.WxBase;
import com.app.framework.wx.service.WxBaseService;
import com.app.framework.wx.wxjson.WxBaseJson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 微信基础接口的实现类
 */
@Service
public class WxBaseServiceImpl implements WxBaseService {

    @Value("${wx.accessToken.url}")
    private String getAccessTokenUrl;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    @Value("${wx.accessToken.expiresTime}")
    private Long expiresTime;

    @Autowired
    private WxBaseJson wxBaseJson;

    private String paramAccesstokenUrl;
    public static WxBase wxBase;
    private volatile static AccessToken accessToken;

    @PostConstruct
    public void init() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                getToken();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 100, expiresTime);
    }

    @Override
    public String getAccessToken() {
        if (null != accessToken) {
            if (!accessToken.isAccessTokenExpired()) {
                return accessToken.getAccessToken();
            }
        }
        return getToken();
    }

    private String getToken() {
        String url = this.getAccessTokenUrl();
        String result = HttpClientUtils.getToUrl(url);
        try {
            accessToken = wxBaseJson.accessTokenJson(result);
        } catch (Exception e) {
            return null;
        }

        return accessToken.getAccessToken();
    }

    public String getAccessTokenUrl() {
        if (StringUtils.isNoneBlank(paramAccesstokenUrl)) {
            return paramAccesstokenUrl;
        }

        wxBase = new WxBase(appid, appsecret);
        final String url = String.format(getAccessTokenUrl, appid, appsecret);
        paramAccesstokenUrl = url;
        return url;
    }
}
