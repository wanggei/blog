package com.example.blog.dao;

import com.example.blog.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao extends BaseDao{

    public User getByPasss(@Param("username") String username);

}
