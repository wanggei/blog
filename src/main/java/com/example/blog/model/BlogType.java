package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogType {
    private int id;
    private int bid;
    private int tyid;
}
