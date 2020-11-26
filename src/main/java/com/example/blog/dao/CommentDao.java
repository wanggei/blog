package com.example.blog.dao;


import com.example.blog.model.Blog;
import com.example.blog.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentDao  extends BaseDao{

    List<Comment> getAllList(@Param("bid") Integer bid);

    Comment getone(@Param("id") Integer id);

    List<Comment>  getoneByparentId(@Param("id") Integer id);
}
