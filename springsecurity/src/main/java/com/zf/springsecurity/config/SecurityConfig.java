package com.zf.springsecurity.config;

import com.zf.springsecurity.filter.JwtAuthenticationTokenFilter;

import com.zf.springsecurity.handle.ZFAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ClassName: SecurityConfig
 * @Description: SpringSecurity配置类
 * @Author: ZF
 * @date: 2022/11/20 14:51
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启权限控制的注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private ZFAccessDeniedHandler accessDeniedHandler;
    /**
     * 自定义密码加密存储 替换默认的
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //放开登录接口
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext （前后端分离不用Session）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/security/login",
                        //登录的同时远程调用的一个接口
                        "/sysService/permission/getPermsByEmployeeId/{id}").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        //配置自定义过滤器，把token校验过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().accessDeniedHandler(new ZFAccessDeniedHandler());
    }


    //使实现类能使用认证
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}