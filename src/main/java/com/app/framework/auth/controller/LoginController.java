package com.app.framework.auth.controller;

import com.app.framework.base.BaseController;
import com.app.framework.core.utils.Response;
import com.app.framework.core.utils.Status;
import com.app.framework.wx.model.WxOAuth;
import com.app.framework.wx.model.WxUserInfo;
import com.app.framework.wx.service.WxOAuthService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController extends BaseController {

    private static final String defaultPass = "123456";
    @Autowired
    private WxOAuthService wxOAuthService;

    @ApiOperation(value = "微信用户登录接口", notes = "微信用户登录接口")
    @RequestMapping("/login")
    public String login(String code, ModelMap map) {
        WxOAuth wxOAuth = wxOAuthService.oauth2AccessToken(code);
        WxUserInfo wxUserInfo = wxOAuthService.getUserInfo(wxOAuth);

        if (null != wxUserInfo && !StringUtils.isEmpty(wxUserInfo.getOpenid())) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(wxUserInfo.getOpenid(), defaultPass);
            subject.login(token);
            map.addAttribute("jsessionid", subject.getSession().getId().toString());
        }
        return "login";
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping("/logout")
    public Response logout() {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        if (principals != null) {
            subject.logout();
        }
        return new Response(Status.SUCCESS.code(), Status.SUCCESS.msg());
    }
}
