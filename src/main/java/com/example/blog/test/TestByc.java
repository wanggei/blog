package com.example.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class TestByc {
    @Test
    public void one(){
        String by= BCrypt.hashpw("111111",BCrypt.gensalt());
        System.out.println(by);
        System.out.println(0.0/0);


    }
}
