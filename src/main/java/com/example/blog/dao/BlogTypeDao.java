package com.example.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogTypeDao extends BaseDao {
    void deleteById(@Param("id") Integer id);
}
