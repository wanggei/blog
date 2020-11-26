package com.example.blog.unitl;

import com.example.blog.model.Blog;

public class BlogUntil {
    public static Blog BlogTrueOrFalse(Blog blog){

        Blog blog1=new Blog();
        blog1.setId(blog.getId());
        blog1.setTitle(blog.getTitle());
        blog1.setContent(blog.getContent());
        blog1.setFlag("原创");
        blog1.setViews(2);
        blog1.setFirstpicture(blog.getFirstpicture());

        blog1.setRecommend(blog.isRecommend());
        blog1.setOpencomment(blog.isOpencomment());
        blog1.setSharestatment(blog.isSharestatment());
        blog1.setPublish(blog.isPublish());
        return blog1;
    }
}
