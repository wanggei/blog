package com.example.blog.service;

import com.example.blog.model.Blog;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogService {
    void insert(Blog blog,Integer uid);

    List<Blog> getAllById(Integer id);

    PageInfo<Blog> getblogPage(Integer id,Integer PageNum, Integer PageSize);

    void deleteByid(Integer bid);

    PageInfo<Blog> getSearchByTitle(String title,Integer PageNum,Integer PageSize);

    Blog getOne( Integer id);

    int upDateBlog (Blog blog);

    PageInfo<Blog> getAllFinally(Integer PageNum,Integer PageSize);

    Blog getOneById( Integer id);

    List<String> getYear();

    List<Blog> getByYear( String year);

    int updateViews(Integer id);

    List<Blog> getBycreatimeDesc();//只获取数据前三条

    PageInfo<Blog> getByTage( String tagename,Integer PageNum,Integer PageSize);//根据标签查询博客

    PageInfo<Blog> getByType(String typeName, Integer pageNum, Integer pageSize);
}
