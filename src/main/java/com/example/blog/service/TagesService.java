package com.example.blog.service;

import com.example.blog.model.Tages;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagesService  {

    List<Tages> getSelectAll();
    PageInfo<Tages> PageTages(Integer pageNum,Integer pageSize);
    void addTage(Tages tages);
    int deleteTage(Integer id);
    PageInfo<Tages> getSearchByNm(Integer pageNum,Integer pageSize,String tage);

    Tages getById(Integer id);

    int upDateById(Tages types);
}
