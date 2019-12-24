package com.ycs.study.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionController {
    @PreAuthorize("hasAuthority('DOCKER')")
    @RequestMapping("/docker")
    public String test1() {
        return "说明你有docker权限";
    }
    @RequestMapping("/custom")
    public String test0() {
        return "说明你有自定义权限";
    }
    @PreAuthorize("hasAuthority('JAVA')")
    @RequestMapping("/java")
    public String test2() {
        return "说明你有java权限";
    }
    @PreAuthorize("hasAuthority('PHP')")
    @RequestMapping("/php")
    public String test3() {
        return "说明你有最好语言的权限";
    }
}