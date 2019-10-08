package com.ycs.study.service.impl;

import com.ycs.study.entity.SysUser;
import com.ycs.study.service.IUserService;
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
        SysUser user = iUserService.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //把角色放入认证器里
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = user.getRoles();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new User(user.getUserName(), user.getPassword(), authorities);
    }
}
