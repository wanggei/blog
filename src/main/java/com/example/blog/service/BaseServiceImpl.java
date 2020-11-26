package com.example.blog.service;

import com.example.blog.dao.BaseDao;
import com.example.blog.unitl.EnityTols;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
    public abstract BaseDao baseDao();

    public Class<?> classze;

    public String tableName;

    public BaseServiceImpl() {
        //通过反射找到继承的类
        classze=(Class<?>) ((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
        tableName="t_"+classze.getSimpleName().toLowerCase();//获取表格名

    }


    @Override
    public T selectOne(int id) {

        return (T) EnityTols.ReturnT(baseDao().selectOne(tableName, id), classze);
    }


    @Override
    public List<T> getAll() {

        List<T> listT=new ArrayList<>();
        List<Map<Object, Object>> map=baseDao().getAll(tableName);

        for(Map<Object, Object> list:map) {

            listT.add((T) EnityTols.ReturnT(list, classze));
        }
        return listT;
    }

    @Override
    public int add(T t) {

        List<Object> list=new ArrayList<>();//将结果封装在集合中
        for(Field filed:t.getClass().getDeclaredFields()) {
            filed.setAccessible(true);
            try {

                list.add(filed.get(t));
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return baseDao().add(tableName, list.toArray());
    }

    public void addForNotMath(Object [] fileName,Object[] fileValue) {

        baseDao().addForNotMath(tableName,fileName,fileValue);
    }

    @Override
    public int update(T t) {
        int id=0;
        List<Object> list=new ArrayList<>();//将结果封装在集合中
        for(Field filed:t.getClass().getDeclaredFields()) {
            filed.setAccessible(true);
            try {
                if(filed.get(t)==null)continue;

                if("id".equals(filed.getName())) {
                    id=(int) filed.get(t);
                    continue;
                }

//				 变量='值'

                list.add(filed.getName()+"='"+filed.get(t)+"'");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return baseDao().update(tableName, id, list.toArray());
    }
    public int updateBlog(T t) {
        int id=0;
        List<Object> list=new ArrayList<>();//将结果封装在集合中
        for(Field filed:t.getClass().getDeclaredFields()) {
            filed.setAccessible(true);
            try {
                if(filed.get(t)==null)continue;
                if("id".equals(filed.getName())) {
                    id=(int) filed.get(t);
                    continue;
                }
                if("types".equals(filed.getName()))continue;
                if("tages".equals(filed.getName()))continue;
                if("users".equals(filed.getName()))continue;
                if("comments".equals(filed.getName()))continue;
                if ("appreciation".equals(filed.getName()))continue;
                if ("Opencomment".equals(filed.getName()))continue;
                if ("sharestatment".equals(filed.getName()))continue;
                if ("publish".equals(filed.getName()))continue;
                if ("recommend".equals(filed.getName()))continue;
//				 变量='值'

                list.add(filed.getName()+"='"+filed.get(t)+"'");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return baseDao().update(tableName, id, list.toArray());
    }

    @Override
    public int delete(int id) {

        return baseDao().delete(tableName, id);
    }


}
