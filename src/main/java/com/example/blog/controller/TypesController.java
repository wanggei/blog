package com.example.blog.controller;


import com.example.blog.model.Blog;
import com.example.blog.model.Types;
import com.example.blog.service.BlogService;
import com.example.blog.service.TypesService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TypesController {
    @Autowired
    private TypesService typesService;
    @Autowired
    private BlogService blogService;

    //删除一条分类
    @RequestMapping("/admin/deleteTypes")
    @ResponseBody
    public String delete(@RequestParam Integer id){
        typesService.deleteById(id);
        return "success";
    }
    //新增一条数据
    @ResponseBody
    @PostMapping(value = "/admin/addtypes")
    public String Addtypes(String typename){
        Types types=new Types();
        types.setName(typename);
        typesService.addTypes(types);
        return "success";
    }
    //模糊查找一条数据
    @GetMapping (value = "/admin/searchType")
    public String getSearch(String classify, Model model){
        int pageNum=1;
        int pageSize=5;
        PageInfo<Types> searchByNm = typesService.getSearchByNm(pageNum, pageSize, classify);
        model.addAttribute("typesPage",searchByNm);
        return "/admin/admintypes";
    }
    //修改一条数据前的操作
    @RequestMapping(value = "/admin/updateTypes")
    public String UpdateTypes(Integer id,Model model){
        Types byId = typesService.getById(id);
        model.addAttribute("type",byId);
        return "/admin/updateTypes";
    }
    @RequestMapping(value = "/admin/UpdateTypeSubmit")
    public String UpdateTypesSubmit(@RequestParam String name,@RequestParam Integer id){
        Types types=new Types();
        types.setName(name);
        types.setId(id);
        typesService.upDateById(types);
        return "redirect:/admin/admintypes ";
    }
    @RequestMapping(value = "/typeLike")
    public String searcheByTage(String typeName ,Model model,Integer PageNum,Integer PageSize){
        if (PageNum==null)PageNum=1;
        if (PageSize==null)PageSize=4;
        PageInfo<Blog> byTage = blogService.getByType(typeName, PageNum, PageSize);
        model.addAttribute("pageblogIndex",byTage);
        return "types";
    }
}
