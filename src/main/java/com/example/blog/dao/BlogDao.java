package com.example.blog.dao;


import com.example.blog.model.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao extends BaseDao {
    List<Blog> getAllById(@Param("id") Integer id);

    Blog getBlogByTitle(@Param("title") String title);//模糊查询

    List<Blog> getSearchByTitle(@Param("title") String title);

    Blog getOne(@Param("id") Integer id);//更新操作用到

    List<Blog> getAll();//首页获取博客用到

    Blog getOneById(@Param("id") Integer id);//博客详情用到

    List<String> getYear();

    List<Blog> getByYear(@Param("year") String year);

    int updateViews( @Param("id") Integer id);

    List<Blog> getBycreatimeDesc();//只获取数据前三条

    List<Blog> getByTage(@Param("tagename") String tagename);

    List<Blog> getByType(@Param("typename") String typename);

}

