package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


public class TokenConfig {
    private static final String SIG_KRY="uua123";

    @Bean
    public TokenStore tokenStore(){

        return new JwtTokenStore(access());
    }

    @Bean
    public JwtAccessTokenConverter access(){

        JwtAccessTokenConverter jwtAccessTokenConverter=new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(SIG_KRY);
        return jwtAccessTokenConverter;
    }
}
