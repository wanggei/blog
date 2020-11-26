package com.example.blog.service;

import com.example.blog.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComment(Integer bid);
    void addComment(Comment comment);
//    Comment  getoneByparentId( Integer id);
}
