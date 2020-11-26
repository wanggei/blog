package com.example.blog.service;

import com.example.blog.dao.BaseDao;
import com.example.blog.model.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blogUserService")
public class BlogUserServiceImpl extends BaseServiceImpl<BlogUser> implements BlogUserService {
    @Autowired
    private BaseDao baseDao;
    @Override
    public BaseDao baseDao() {
        return baseDao;
    }


}
