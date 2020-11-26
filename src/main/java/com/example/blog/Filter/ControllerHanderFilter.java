package com.example.blog.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerHanderFilter {

    private  final Logger logger= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest request, Exception e) throws Exception {
        logger.error("Request URL :{},Exception : {}",request.getRequestURL(),e);
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("Request URl ",request.getRequestURL());
        modelAndView.addObject("Execption",e);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
