package com.app.framework.auth.controller;

import com.app.framework.base.BaseController;
import com.app.framework.core.utils.Response;
import com.app.framework.core.utils.Status;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class TestController extends BaseController {

    @RequestMapping("/test")
    public List<Map<String,Object>> login() {
        List<Map<String,Object>>list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("address","蜀山");
        map.put("date","20180413");
        map.put("name","杨大侠");
        list.add(map);
        return list;
    }
}
