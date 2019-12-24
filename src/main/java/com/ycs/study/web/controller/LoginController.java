package com.ycs.study.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chengshuoyu
 */
@Controller
@RequestMapping
public class LoginController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * security默认访问地址/login, 默认的登录页面
     * @return
     */
    @RequestMapping("/login1")
    public String login() {
        return "login";
    }
}
