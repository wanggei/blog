package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.model.Tages;
import com.example.blog.model.Types;
import com.example.blog.model.User;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagesService;
import com.example.blog.service.TypesService;
import com.example.blog.service.UserDelitService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {


    @Autowired
    private TypesService typesService;
    @Autowired
    private TagesService tagesService;
    @Autowired
    private BlogService blogService;




    @RequestMapping(value = "/index")
    public String indexsuccess(HttpSession session ,Model model){
        model.addAttribute("newblog",blogService.getBycreatimeDesc());
       User user=(User) session.getAttribute("user");
       if (user.getType()==1){
           return "/admin/index";
       }else {
           return "redirect:/";
       }

    }

    @RequestMapping(value = "/admin/release")
    public String release(Model model){
        model.addAttribute("tages",tagesService.getSelectAll());
        model.addAttribute("types",typesService.getSelectAll());
        return "/admin/release";
    }

    @RequestMapping(value = "/admin/adminblog")
    public String adminblog(HttpSession session,Integer PageNum,Integer PageSize,Model model){
        User user=(User)session.getAttribute("user");
        int id=user.getId();
        if (PageNum==null)PageNum=1;
        if (PageSize==null)PageSize=5;
        PageInfo<Blog> blogall = blogService.getblogPage(id,PageNum,PageSize);
        model.addAttribute("blogPage",blogall);
        return "/admin/adminblog";
    }
    @RequestMapping(value = "/admin/archived")
    public String archived(Model model){
        Map<String ,List<Blog>> map=new HashMap<>();
        List<String> years = blogService.getYear();
        int length=0;
        for (String year:years){
            List<Blog> byYear = blogService.getByYear(year);
            length=length+byYear.size();
            map.put(year,byYear);
        }
        model.addAttribute("map",map);
        model.addAttribute("length",length);
        return "/admin/archived";
    }

    //进入分类操作页面
    @RequestMapping(value = "/admin/admintypes")
    public String admintypes(Model model,Integer pageSize,Integer pageNum){
        if (pageNum==null)pageNum=1;
        if (pageSize==null)pageSize=5;
        PageInfo<Types> typesPage=typesService.PageTypes(pageNum,pageSize);
        System.out.println(typesPage);
        model.addAttribute("typesPage",typesPage);
        return "/admin/admintypes";
    }
   @RequestMapping(value = "/admin/admintage")
    public String admintages(Model model,Integer pageNum,Integer pageSize){
        if (pageNum==null)pageNum=1;
        if (pageSize==null)pageSize=5;
        PageInfo<Tages> TagepageInfo = tagesService.PageTages(pageNum, pageSize);
        model.addAttribute("tagesPage",TagepageInfo);
        return "/admin/admintages";
    }
    @GetMapping(value = "/admin/newblogList")
    public String newblogList(Model model){
        model.addAttribute("newblog",blogService.getBycreatimeDesc());
        return " /admin/_fragment:: newblogList";
    }
}
