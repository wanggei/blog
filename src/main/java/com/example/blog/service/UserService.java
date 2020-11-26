package com.example.blog.service;

import com.example.blog.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserService  {

    public User getByPasss(@Param("username") String username);


}
