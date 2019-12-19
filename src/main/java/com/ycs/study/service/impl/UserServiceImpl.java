package com.ycs.study.service.impl;

import com.ycs.study.entity.SysUser;
import com.ycs.study.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>com.ycs.study.service.impl</p>
 * <p>ClassName: UserServiceImpl</p>
 * <p>Description: TODO</p>
 * <p>Author: Chengshuo Yu</p>
 * <p>Date: 2019/9/27 17:07</p>
 * <p>Version: v1.0</p>
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Set<SysUser> users = new HashSet<>();

/*
    static {
        users.add(new SysUser(1L, "fulin", "123456", Arrays.asList("ROLE_ADMIN", "ROLE_DOCKER")));
        users.add(new SysUser(2L, "xiaohan", "123456", Arrays.asList("ROLE_ADMIN", "ROLE_DOCKER")));
        users.add(new SysUser(3L, "longlong", "123456", Arrays.asList("ROLE_ADMIN", "ROLE_DOCKER")));
    }*/

    @Override
    public SysUser findByUsername(String userName) {
        return users.stream().filter(o -> StringUtils.equals(o.getUserName(), userName)).findFirst().orElse(null);
    }
}
