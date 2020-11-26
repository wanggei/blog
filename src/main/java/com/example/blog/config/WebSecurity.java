package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }
    //安全拦截
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/index").hasAuthority("1")
                //.antMatchers("/admin/login").hasAuthority(null)
                .antMatchers("/admin/**").hasAuthority("1")
                .anyRequest().permitAll()
                .and()
                .formLogin()//允许表单登录
                .loginPage("/login")//提交的页面
                .loginProcessingUrl("/admin/login")//提交的地址
                .successForwardUrl("/index")//表单成功登录自定义跳转页面
                .failureForwardUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");


    }
}
