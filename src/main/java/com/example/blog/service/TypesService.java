package com.example.blog.service;

import com.example.blog.model.Types;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypesService {

    public List<Types> getSelectAll();

    public Types getById(@Param("id") Integer id);

    public int  upDateById(@Param("types") Types types);

    public int deleteById(@Param("id") Integer id);

    public void addTypes(Types types);

    PageInfo<Types> getSearchByNm(Integer pageNum,Integer pageSize, String name);//模糊查询

    PageInfo<Types>PageTypes(Integer pageNum,Integer pageSize);

}
