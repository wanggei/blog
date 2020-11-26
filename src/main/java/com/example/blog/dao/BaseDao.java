package com.example.blog.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BaseDao {

    //增删查改

    public int add(@Param("tableName") String tableName, @Param("Object") Object[] objects);

    public int delete(@Param("tableName") String tableName, @Param("id") Integer id);

    public int update(@Param("tableName") String tableName, @Param("id") Integer id, @Param("Object") Object[] objects);

    public Map<Object,Object> selectOne(@Param("tableName") String tableName, @Param("id") Integer id);

    public List<Map<Object,Object>> getAll(@Param("tableName") String tableName);

    public void addForNotMath(@Param("tableName") String tableName);

    public void addForNotMath(@Param("tableName") String tableName, @Param("fileName") Object[] fileName, @Param("fileValues") Object[] fileValues);
}
