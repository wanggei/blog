package com.example.blog.service;

import com.example.blog.dao.BaseDao;
import com.example.blog.model.BlogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blogTypeService")
public class BlogTypeServiceImpl extends BaseServiceImpl<BlogType> implements BlogTypeService{

    @Autowired
    private BaseDao baseDao;
    @Override
    public BaseDao baseDao() {
        return baseDao;
    }
}
