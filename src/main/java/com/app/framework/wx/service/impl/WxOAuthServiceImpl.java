package com.app.framework.wx.service.impl;

import com.app.framework.core.utils.HttpClientUtils;
import com.app.framework.core.utils.Log;
import com.app.framework.core.utils.LoggerFactory;
import com.app.framework.wx.model.WxBase;
import com.app.framework.wx.model.WxOAuth;
import com.app.framework.wx.model.WxUserInfo;
import com.app.framework.wx.service.WxOAuthService;
import com.app.framework.wx.wxjson.WxOAuthJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * 微信公众号网页授权实现类
 */
@Service
public class WxOAuthServiceImpl implements WxOAuthService {
    protected Log logger = LoggerFactory.getLogger(getClass());


    @Value("${wx.oauth.getCode.url}")
    private String getCodeUrl;
    @Value("${wx.oauth.redirect.url}")
    private String redirectUri;
    @Value("${wx.oauth.getAccessToken.url}")
    private String oauthAccessTokenUrl;
    @Value("${wx.oauth.userInfo.url}")
    private String userInfoUrl;

    @Autowired
    private WxOAuthJson wxOAuthJson;


    @Override
    public String oauth2LoginUrl() {
        String url = "";
        try {
            url = String.format(getCodeUrl, WxBaseServiceImpl.wxBase.getAppid(), redirectUri, "snsapi_userinfo");
        } catch (Exception e) {
            logger.error("获取微信信息出错！", e);
        }
        return url;

    }

    @Override
    public WxOAuth oauth2AccessToken(String code) {
        WxBase wxBase = WxBaseServiceImpl.wxBase;
        WxOAuth wxOAuth = null;
        if (null == wxBase) {

        }
        try {
            String url = String.format(oauthAccessTokenUrl, wxBase.getAppid(), wxBase.getAppsecret(), code);
            String result = HttpClientUtils.getToUrl(url);
            wxOAuth = wxOAuthJson.oauthAccessTokn(result);

        } catch (Exception e) {
        }

        return wxOAuth;
    }

    @Override
    public WxUserInfo getUserInfo(WxOAuth wxOAuth) {
        String url = String.format(userInfoUrl, wxOAuth.getAccessToken(), wxOAuth.getOpenid());
        String result = HttpClientUtils.getToUrl(url);
        logger.info("获取用户详细信息={}", result);
        return wxOAuthJson.userInfoJson(result);
    }


}
