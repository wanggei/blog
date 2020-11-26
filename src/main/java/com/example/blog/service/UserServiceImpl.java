package com.example.blog.service;

import com.example.blog.dao.BaseDao;
import com.example.blog.dao.UserDao;
import com.example.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private BaseDao baseDao;
    @Autowired
    private UserDao userDao;

    @Override
    public BaseDao baseDao() {
        return baseDao;
    }


    @Override
    public User getByPasss(String username) {
        return userDao.getByPasss(username);
    }
}
