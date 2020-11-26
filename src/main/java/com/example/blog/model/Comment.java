package com.example.blog.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int id;
    private String nickename;//昵称
    private String email;//邮箱
    private String content;//评论内容
    private String avator;//头像
    private Date creatTime;//评论时间
    private Blog blog;
    private List<Comment> replyComment;//子级评论
    private Comment ParentCommment;//父级评论
}
