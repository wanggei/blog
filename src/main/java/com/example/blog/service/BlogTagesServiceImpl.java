package com.example.blog.service;

import com.example.blog.dao.BaseDao;
import com.example.blog.model.BlogTage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blogTagesService")
public class BlogTagesServiceImpl extends BaseServiceImpl<BlogTage> implements BlogTagesService{

    @Autowired
    private BaseDao baseDao;
    @Override
    public BaseDao baseDao() {
        return baseDao;
    }
}
