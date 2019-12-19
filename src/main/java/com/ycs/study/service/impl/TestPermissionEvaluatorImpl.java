package com.ycs.study.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("testPermissionEvaluator")
public class TestPermissionEvaluatorImpl implements TestPermissionEvaluator {

    @Override
    public boolean check(Authentication authentication) {
        //这里可以拿到登陆信息然后随便的去定制自己的权限 随便你怎么查询
        //true就是过，false就是不过
        System.out.println("进入了自定义的匹配器" + authentication);
        return false;
    }
}

interface TestPermissionEvaluator {
    boolean check(Authentication authentication);
}
