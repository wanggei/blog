package com.example.blog.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tages {
    private int id;
    private  String tage;
    private List<Blog> blogs;
}
