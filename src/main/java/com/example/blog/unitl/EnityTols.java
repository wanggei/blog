package com.example.blog.unitl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnityTols {

    private static Map<String, EntityCacheItem> mapss=new HashMap<>();

    public static <T> T ReturnT(Map<Object, Object> map, Class<T> enitys) {
        //尝试从缓存中取得集合
        EntityCacheItem cacheItem= mapss.get(enitys.getName());

        if(cacheItem==null) {
            cacheItem=EntityCacheItem.cacheItem(enitys);
            mapss.put(enitys.getName(), cacheItem);
        }



//		先定义一个List集合
        List<String> list=cacheItem.getList();
//		在定义一个map集合

        Map<String,Method> maps=cacheItem.getMaps();

        String key;
        String key1;
        String key2;
        Map<Object,Object > newmap=new HashMap<>();
        for(Map.Entry<Object, Object> entry:map.entrySet()) {
            key=entry.getKey().toString();
            //add_data
            while(key.contains("_")) {
                key1=key.substring(0,key.indexOf("_"));//add
                key2=key.substring(key.indexOf("_")+1);//data
                key=key1+key2.substring(0, 1).toUpperCase()+key2.substring(1);

            }
            newmap.put(key, entry.getValue());
        }
//		获取enitys对象里面的属性集合

        T enity=null;
        Object parament=null;

        Method method=null;
        Class<?>[] paramentType=null;
        try {
            enity= (T) enitys.newInstance();
            //遍历List集合获取属性名称
            for(String filedname:list) {

                //通过字段名去获取数据库传过来的map对应的值
                parament= newmap.get(filedname);

                if(parament==null) continue;
                //获取对应的set方法
                method= maps.get(filedname);

                if(method==null)continue;

                paramentType=method.getParameterTypes();//该返回方法的参数类型

                if(paramentType==null || paramentType.length>1)continue;

                //isAssignableFrom方法是判断是否为某个类的父类
                if(paramentType[0].isAssignableFrom(parament.getClass())) {
                    //一致
                    try {
                        method.invoke(enity, parament);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return enity;

    }

    //	建立缓存机制
    static class EntityCacheItem {

        //先获取enitys的对象属性名称用List集合封装
        //获取enitys对象的set方法名字用map集合封装

        //		先定义一个List集合
        private List<String> list=new ArrayList<>();
        //		在定义一个map集合
        private Map<String,Method> maps=new HashMap<>();

        public EntityCacheItem() {

        }

        public List<String> getList() {
            return list;
        }
        public Map<String, Method> getMaps() {
            return maps;
        }

        public void parseEntry(Class<?> entityclass) {
//			获取enitys对象里面的属性集合
            Field[] allFiled=entityclass.getDeclaredFields();
//			遍历属性集合
            String name=null;
            String Setname=null;
            Method setMethod = null;
            for(Field filed:allFiled) {
                filed.setAccessible(true);//打开权限
                name=filed.getName();
                list.add(name);
                Setname="set"+name.substring(0, 1).toUpperCase()+name.substring(1);
                try {
                    setMethod=entityclass.getDeclaredMethod(Setname, filed.getType());//获取set方法
                } catch (NoSuchMethodException e) {

                    e.printStackTrace();
                } catch (SecurityException e) {

                    e.printStackTrace();
                }
                maps.put(name, setMethod);
            }
        }
        public static EntityCacheItem cacheItem(Class<?> entityclass) {
            EntityCacheItem entityCacheItem =new EntityCacheItem();
            entityCacheItem.parseEntry(entityclass);

            return entityCacheItem;
        }
    }

}

