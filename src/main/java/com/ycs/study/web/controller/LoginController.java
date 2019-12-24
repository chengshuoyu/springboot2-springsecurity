package com.ycs.study.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class TestController {


    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/login1")
    public String login() {
        return "login";
    }

    @ResponseBody
    @GetMapping("/test")
    public String test(){
        return "test ok";
    }
}
