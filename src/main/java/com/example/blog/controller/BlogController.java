package com.example.blog.controller;


import com.example.blog.model.Blog;
//import com.example.blog.service.BlogService;

import com.example.blog.model.Tages;
import com.example.blog.model.Types;
import com.example.blog.model.User;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagesService;
import com.example.blog.service.TypesService;
import com.example.blog.unitl.TageUntis;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BlogController {


    @Autowired
    private BlogService blogService;
    @Autowired
    private TypesService typesService;
    @Autowired
    private TagesService tagesService;
    //添加一条博客
    @PostMapping(value = "/admin/addblog")
    public String addBlog(Blog blog, HttpSession session, String tageId,String typeId){
        blog.setTages(TageUntis.StringTurnList(tageId));
        Types types=new Types();
        types.setId(Integer.valueOf(typeId));
        blog.setTypes(types);
        blog.setCreatetime(new Date());
        User user = (User)session.getAttribute("user");
        int uid=user.getId();
        blogService.insert(blog,uid);
       return "redirect:/admin/adminblog";
    }
    //删除一条博客
    @ResponseBody
    @RequestMapping(value = "/admin/deleteBlog")
    public String deleteBlog(Integer id){
        blogService.deleteByid(id);
        return "success";
    }
    //查询一条数据
    @RequestMapping(value = "/admin/searchBlog")
    public String search(String title, Model model){
      int PageNum=1;
      int PageSize=5;
      PageInfo<Blog> searchByTitle = blogService.getSearchByTitle(title, PageNum, PageSize);
      model.addAttribute("blogPage",searchByTitle);
      return "/admin/adminblog";
    }
    //更新一条数据
    @RequestMapping(value = "/admin/updateblog")
    public String updateblog(Integer id,Model model){
        Blog one = blogService.getOne(id);
        String tages= TageUntis.division(one.getTages());
        model.addAttribute("tagesId",tages);
        model.addAttribute("tages",tagesService.getSelectAll());
        model.addAttribute("types",typesService.getSelectAll());
        model.addAttribute("blog",one);
        return "/admin/releaseinput";
    }
    @RequestMapping(value = "/admin/updat")
    public String updateSubmit(Blog blog ,String tageId,String typeId){
        blog.setTages(TageUntis.StringTurnList(tageId));
        Types types=new Types();
        types.setId(Integer.valueOf(typeId));
        blog.setTypes(types);
        blogService.upDateBlog(blog);
        return "redirect:/admin/adminblog";
    }


}
