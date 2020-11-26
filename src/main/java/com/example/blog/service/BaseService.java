package com.example.blog.service;

import java.util.List;

public interface BaseService<T> {
    public int add(T t);
    public int update(T t);
    public int delete(int i);
    public T selectOne(int i );
    public List<T> getAll();
}

