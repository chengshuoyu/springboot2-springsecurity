package com.ycs.study.service.impl;

import com.ycs.study.entity.SysRole;
import com.ycs.study.entity.SysUser;
import com.ycs.study.init.InitData;
import com.ycs.study.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>com.ycs.study.service.impl</p>
 * <p>ClassName: UserService</p>
 * <p>Description: TODO</p>
 * <p>Author: Chengshuo Yu</p>
 * <p>Date: 2019/9/27 17:12</p>
 * <p>Version: v1.0</p>
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserService iUserService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //SysUser user = iUserService.findByUsername(s);
        SysUser user = InitData.SYS_USERS.stream().filter(o-> StringUtils.equals(o.getUserName(), s)).findFirst().orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        //把角色放入认证器里
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<SysRole> roles = user.getRoles();
        for (SysRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new User(user.getUserName(), user.getPassword(), authorities);
    }
}
