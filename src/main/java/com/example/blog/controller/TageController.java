package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.model.Tages;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagesService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TageController {

    @Autowired
    private TagesService tagesService;

    @Autowired
    private BlogService blogService;

    //新增一条数据
    @PostMapping(value = "/admin/addtages")
    @ResponseBody
    public String addTage(@RequestParam String tagename, HttpSession session){
        Tages tages=new Tages();
        tages.setTage(tagename);
        tagesService.addTage(tages);
        session.setAttribute("message","成功");
        return "success";
    }
    //删除一条数据
    @PostMapping("/admin/deleteTages")
    @ResponseBody
    public String deleteTage(Integer id){
        tagesService.deleteTage(id);
        return "success";
    }
    //模糊查询
    @GetMapping(value = "/admin/searchTage")
    public String searchTage(Model model,String tage){
        int pageNum=1;
        int pageSize=5;
        PageInfo<Tages> pageInfo = tagesService.getSearchByNm(pageNum,pageSize,tage);
        model.addAttribute("tagesPage",pageInfo);
        return "/admin/admintages";
    }
    //修改一条数据前的操作
    @RequestMapping(value = "/admin/updateTages")
    public String UpdateTages(Integer id,Model model){
        Tages byId = tagesService.getById(id);
        model.addAttribute("tage",byId);
        return "/admin/updateTages";
    }
    @RequestMapping(value = "/admin/UpdateTageSubmit")
    public String UpdateTageSubmit(@RequestParam String name,@RequestParam Integer id){
        Tages tages=new Tages();
        tages.setTage(name);
        tages.setId(id);
        tagesService.upDateById(tages);
        return "redirect:/admin/admintage ";
    }
    @RequestMapping(value = "/tageLike")
    public String searcheByTage(String tageName ,Model model,Integer PageNum,Integer PageSize){
        if (PageNum==null)PageNum=1;
        if (PageSize==null)PageSize=4;
        PageInfo<Blog> byTage = blogService.getByTage(tageName, PageNum, PageSize);
        model.addAttribute("pageblogIndex",byTage);
        return "tags";
    }
}
