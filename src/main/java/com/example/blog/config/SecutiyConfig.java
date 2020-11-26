package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class SecutiyConfig  extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
    @Bean//认证管理器
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    //安全拦截
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/r/r2").hasAuthority("p1")
                .antMatchers("/r/r1").hasAuthority("p2")
                .anyRequest().permitAll()
                .and()
                .formLogin();//允许表单登录
//                .loginPage("/login-view")//提交的页面
//                .loginProcessingUrl("/login")//提交的地址
//                .successForwardUrl("/login-success")//表单登录自定义跳转页面
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login-view?logout");


    }
}
