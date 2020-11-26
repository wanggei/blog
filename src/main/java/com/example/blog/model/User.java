package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String username;//用户名
    private String password;//密码
    private String email;//邮箱
    private Integer type;//类型
    private String  avator;//头像
    private Date time;//创建时间
    private  Date updateTime;//更新时间
    private List<Blog> blogs;
}
