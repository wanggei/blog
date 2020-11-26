package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.model.Tages;
import com.example.blog.model.Types;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagesService;
import com.example.blog.service.TypesService;
import com.example.blog.unitl.MarkDownUnitl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private TypesService typesService;
    @Autowired
    private TagesService tagesService;
    @Autowired
    private BlogService blogService;
    @RequestMapping(value = "/")
    public String index(Model model , HttpSession session,Integer PageNum,Integer PageSize){
        if (PageNum==null)PageNum=1;
        if (PageSize==null)PageSize=4;
        List<Types> selectAll = typesService.getSelectAll();
        model.addAttribute("AllType",selectAll);
        List<Tages> getTages = tagesService.getSelectAll();
        model.addAttribute("AllTages",getTages);
        PageInfo<Blog> allFinally = blogService.getAllFinally(PageNum, PageSize);
        System.out.println(allFinally);
        model.addAttribute("pageblogIndex",allFinally);
        return "index";
    }
    @RequestMapping(value = "/blogmessage")
    public String blog(Integer id ,Model model){

        Blog one = blogService.getOneById(id);
        blogService.updateViews(one.getId());
        String content = one.getContent();
        String s = MarkDownUnitl.markdownToHtmlExtensions(content);
        one.setContent(s);
        model.addAttribute("blogOneInfo",one);
        return "blog";
    }
    @RequestMapping(value = "/aboutme")
    public String aboutme(){

        return "aboutme";
    }


    @RequestMapping(value = "/tags")
    public String tags(Model model ,Integer PageNum,Integer PageSize){
        if (PageNum==null)PageNum=1;
        if (PageSize==null)PageSize=4;
        List<Tages> getTages = tagesService.getSelectAll();
        model.addAttribute("AllTages",getTages);
        PageInfo<Blog> allFinally = blogService.getAllFinally(PageNum, PageSize);
        model.addAttribute("pageblogIndex",allFinally);
        return "tags";
    }
    @RequestMapping(value = "/types")
    public String types(Model model,Integer PageNum,Integer PageSize){
        if (PageNum==null)PageNum=1;
        if (PageSize==null)PageSize=4;
        List<Types> selectAll = typesService.getSelectAll();
        PageInfo<Blog> allFinally = blogService.getAllFinally(PageNum, PageSize);
        model.addAttribute("pageblogIndex",allFinally);
        model.addAttribute("AllType",selectAll);
        return "types";
    }
    @RequestMapping("/login")
    public String index(){

        return "login";
    }

    @GetMapping(value = "/newblogList")
    public String newblogList(Model model){
        model.addAttribute("newblog",blogService.getBycreatimeDesc());
        return " _fragment:: newblogList";
    }
}
