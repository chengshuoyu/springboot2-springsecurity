package com.ycs.study.config;

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
                .loginPage("/login1")// //指定自定义登录页面，不指定则使用sucerity自带的默认配置的 /login, 跳转到到默认的登录页面
                .loginProcessingUrl("/authentication/form1") // 自定义登录路径 登录请求url-form表单的action
                .failureHandler(failureAuthenticationHandler) // 自定义登录失败处理
                .successHandler(successAuthenticationHandler) // 自定义登录成功处理
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .authorizeRequests()// 对请求授权
                 // 这些页面不需要身份认证,其他请求需要认证
                .antMatchers("/login1", "/authentication/require", "/test",
                        "/authentication/form1")
                .permitAll()
                .anyRequest() // 任何请求
                .authenticated() // 都需要身份认证
                .and()
                .rememberMe()
                .rememberMeCookieName("remember-ME")
                .tokenValiditySeconds(3600) //cookie存活时间
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