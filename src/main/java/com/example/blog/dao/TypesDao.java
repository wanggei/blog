package com.example.blog.dao;


import com.example.blog.model.Types;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypesDao extends BaseDao{
    List<Types> getAll();

    List<Types> getSearchByNm(@Param("name") String name);

    Types getOne(@Param("id") Integer id);


}
