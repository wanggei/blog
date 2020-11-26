package com.example.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.annotation.RequestScope;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExecption extends RuntimeException {

 public NotFoundExecption (){}
 public NotFoundExecption(String messge){
     super(messge);
 }
    public NotFoundExecption(String messge ,Throwable cause){
        super(messge,cause);
    }

}
