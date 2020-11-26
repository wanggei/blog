package com.example.blog.service;

import com.example.blog.dao.BaseDao;
import com.example.blog.dao.TagesDao;
import com.example.blog.model.Tages;
import com.example.blog.model.Types;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagesService")
public class TagesServiceImpl extends BaseServiceImpl<Tages> implements TagesService {

    @Autowired
    private BaseDao baseDao;
    @Autowired
    private TagesDao tagesDao;
    @Override
    public BaseDao baseDao() {
        return baseDao;
    }

    @Override
    public List<Tages> getSelectAll() {
        return tagesDao.getAll();
    }

    @Override
    public PageInfo<Tages> PageTages(Integer pageNum, Integer pageSize) {

        List<Tages> pageHelper=PageHelper.startPage(pageNum,pageSize);
        List<Tages> all = tagesDao.getAll();
        PageInfo<Tages> pageInfo=new PageInfo<>(all);
        return pageInfo;
    }

    @Override
    public void addTage(Tages tages) {
        this.addForNotMath(new Object[]{"id","tage"},new Object[]{null,tages.getTage()});
    }

    @Override
    public int deleteTage(Integer id) {
        return this.delete(id);
    }

    @Override
    public PageInfo<Tages> getSearchByNm(Integer pageNum, Integer pageSize, String tage) {

        List<Tages> pageHelper=PageHelper.startPage(pageNum,pageSize);
        List<Tages> searchByNm = tagesDao.getSearchByNm(tage);
        PageInfo<Tages> pageInfo=new PageInfo<>(searchByNm);
        return pageInfo;
    }

    @Override
    public Tages getById(Integer id) {
        return tagesDao.getOne(id);
    }

    @Override
    public int upDateById(Tages types) {

        return this.update(types);
    }
}
