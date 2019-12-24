package com.ycs.study.init;

import com.ycs.study.entity.SysRole;
import com.ycs.study.entity.SysUser;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class InitData {

    public static final Set<SysUser> SYS_USERS = new HashSet<>();

    public static final Set<SysRole> SYS_ROLES = new HashSet<>();

    static {
        SYS_ROLES.add(new SysRole(1L, "JAVA"));
        SYS_ROLES.add(new SysRole(2L, "DOCKER"));
        SYS_ROLES.add(new SysRole(3L, "PHP"));
        SYS_ROLES.add(new SysRole(4L, "PYTHON"));
        SYS_ROLES.add(new SysRole(5L, "ENTOS"));
    }

    static {
        SYS_USERS.add(
                new SysUser(1L, "fulin", "a3caed36f0fe5a01e5f144db8927235e",
                        SYS_ROLES.stream().filter(o -> StringUtils.equalsAny(o.getRoleName(), "JAVA", "DOCKER")).collect(Collectors.toList())
                )
        );
        SYS_USERS.add(
                new SysUser(2L, "maoxiansheng", "a3caed36f0fe5a01e5f144db8927235e",
                        SYS_ROLES.stream().filter(o -> StringUtils.equalsAny(o.getRoleName(), "PHP", "DOCKER")).collect(Collectors.toList())
                )
        );
        SYS_USERS.add(
                new SysUser(3L, "happy fish", "a3caed36f0fe5a01e5f144db8927235e",
                        SYS_ROLES.stream().filter(o -> StringUtils.equalsAny(o.getRoleName(), "PYTHON", "CENTOS")).collect(Collectors.toList())
                )
        );
    }

}
