package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    private int id;
    private String title;//标题
    private String content;//内容
    private String firstpicture;//首图
    private String flag;//标记
    private int views;//浏览次数
    private boolean appreciation;//是否开启赞赏
    private boolean Opencomment;//是否开启评论
    private boolean sharestatment;//是否开启转载
    private boolean publish;//是否发布
    private boolean recommend;//是否推荐
    private Date createtime;//发布时间
    private Date updatetime;//更新时间
    private String blogdescribe;
    private Types types;
    private List<Tages> tages;
    private User users;
    private List<Comment> comments;
}
