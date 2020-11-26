package com.example.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.example.blog.model.User;


@Service("userDelit")
public class UserDelitService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByPasss(username);
        String password=user.getPassword();
        Integer type=user.getType();
        UserDetails userDetails= org.springframework.security.core.userdetails.User.withUsername(username).password(password).authorities(type.toString()).build();
        String password1 = userDetails.getPassword();
        if(password1.equals(password)){
            ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request=servletRequestAttributes.getRequest();
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            return userDetails;
        }else {
            return userDetails;
        }
    }
}
