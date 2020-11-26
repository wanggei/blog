package com.example.blog.service;

import com.example.blog.dao.BaseDao;
import com.example.blog.dao.TypesDao;
import com.example.blog.model.Types;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("typesService")
public class TypesServiceImpl extends  BaseServiceImpl<Types> implements TypesService {

    @Autowired
   private BaseDao baseDao;
    @Autowired
    private TypesDao typesDao;
    @Override
    public BaseDao baseDao() {
        return baseDao;
    }


    @Override
    public List<Types> getSelectAll() {
        return typesDao.getAll();
    }

    @Override
    public Types getById(Integer id) {
        return typesDao.getOne(id);
    }

    @Override
    public int upDateById(Types types) {
        return this.update(types);
    }

    @Override
    public int deleteById(Integer id) {
        return this.delete(id);
    }

    @Override
    public void addTypes(Types types) {
        this.addForNotMath(new Object[]{"id","name"},new Object[]{null,types.getName()});
    }

    @Override
    public PageInfo<Types> getSearchByNm(Integer pageNum, Integer pageSize, String name) {
        List<Types> page=PageHelper.startPage(pageNum,pageSize);
        List<Types> all = typesDao.getSearchByNm(name);
        PageInfo<Types> pageInfo=new PageInfo<>(all);
        return pageInfo;
    }


    @Override
    public PageInfo<Types> PageTypes(Integer pageNum, Integer pageSize) {
        List<Types> page=PageHelper.startPage(pageNum,pageSize);
        List<Types> all = typesDao.getAll();
        PageInfo<Types> pageInfo=new PageInfo<>(all);
        return pageInfo;
    }


}
