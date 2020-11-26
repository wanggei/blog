package com.example.blog.dao;


import com.example.blog.model.Tages;
import com.example.blog.model.Types;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagesDao extends BaseDao {
    List<Tages> getAll();

    List<Tages> getSearchByNm(@Param("tage") String tage);

    Tages getOne(@Param("id") Integer id);


}
