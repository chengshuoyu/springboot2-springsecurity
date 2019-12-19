package com.ycs.study.config;

import com.ycs.study.handler.AccessDeniedAuthenticationHandler;
import com.ycs.study.handler.FailureAuthenticationHandler;
import com.ycs.study.handler.SuccessAuthenticationHandler;
import com.ycs.study.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FailureAuthenticationHandler failureAuthenticationHandler;
    @Autowired
    private SuccessAuthenticationHandler successAuthenticationHandler;
    @Autowired
    private AccessDeniedAuthenticationHandler accessDeniedAuthenticationHandler;
    @Autowired
    private UserService userService;


    /**
     * 注入身份管理器bean
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 把userService 放入AuthenticationManagerBuilder 里
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(
                new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        return charSequence.toString();
                    }

                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        return s.equals(charSequence.toString());
                    }
                });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.httpBasic()  //httpBasic 登录
        http.formLogin()
                 //指定自定义登录页面，不指定则使用sucerity自带的默认配置的 /login, 跳转到到默认的登录页面
                .loginPage("/login1")
                // 自定义登录路径 登录请求url-form表单的action
                .loginProcessingUrl("/authentication/form1")
                // 自定义登录失败处理
                .failureHandler(failureAuthenticationHandler)
                // 自定义登录成功处理
                .successHandler(successAuthenticationHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .authorizeRequests()// 对请求授权
                 // 这些请求不需要身份认证,其他请求需要认证
                .antMatchers("/login1", "/authentication/require", "/test",
                        "/authentication/form1")
                .permitAll() //这些请求不需要验证
                //============权限配置start===========
                //hasRole 用户存的角色名需要带ROLE_, 此处去掉前缀，这个确实不友好，换成hasAuthority
                //hasAuthority([authority])	等同于hasRole,但不会带有ROLE_前缀,不管是Role还是Authority最后都调用hasAuthorityName
//                .antMatchers("/docker").hasRole("DOCKER") //用户拥有制定的角色时返回true （Spring security默认会带有ROLE_前缀）
//                .antMatchers("/java").hasRole("JAVA")
//                .antMatchers("/php").hasRole("PHP")
//                .antMatchers("/python").hasRole("PYTHON")
                .antMatchers("/docker").hasAuthority("DOCKER") //用户拥有制定的角色时返回true （Spring security默认会带有ROLE_前缀）
                .antMatchers("/java").hasAuthority("JAVA")
                .antMatchers("/php").hasAuthority("PHP")
                .antMatchers("/python").hasAuthority("PYTHON")
                // 自定义权限验证逻辑
                .antMatchers("/custom")
                .access("@testPermissionEvaluator.check(authentication)")
                //============权限配置end=============
                .anyRequest() // 任何请求
                .authenticated() // 都需要身份认证
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedAuthenticationHandler)  //权限访问拒绝处理器,默认是跳转页面，此处是返回json数据
                .and()
                .rememberMe()
                .rememberMeCookieName("remember-ME")
                .tokenValiditySeconds(3600)  //cookie存活时间
                .and()
                .csrf()
                .disable();// 禁用跨站攻击
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

}