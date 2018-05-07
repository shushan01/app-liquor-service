package com.app.framework.wx;

import com.app.framework.wx.service.WxBaseService;
import com.app.framework.wx.service.WxOAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;

@Controller
public class AuthorizationController extends HttpServlet {
    @Autowired
    private WxBaseService wxBaseService;
    @Autowired
    private WxOAuthService wxOAuthService;

    /**
     * 获得accessToken接口
     *
     * @return
     */
    @RequestMapping("/base/getAccessToken")
    @ResponseBody
    public String wx() {
        return wxBaseService.getAccessToken();
    }

    /**
     * 获取网页授权登录的第一步地址
     *
     * @return
     */
    @RequestMapping("/oauth/getOAuth2FirstLoginUrl")
    @ResponseBody
    public String getOAuth2FirstLoginUrl() {
        return wxOAuthService.oauth2LoginUrl();
    }
}
