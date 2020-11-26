package com.example.blog.aspect;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AspectController {

    private Logger logger=Logger.getLogger(this.getClass());

    @Pointcut("execution(* com.example.blog.*.*(..))")
    public void log(){}

    @Before("log()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=servletRequestAttributes.getRequest();
        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();
        Object[] objects=joinPoint.getArgs();
        String methodName=joinPoint.getSignature().getName();
        Retrul retrul=new Retrul(url,ip,methodName,objects);
        logger.info("----------before--"+request);
    }
    @After("log()")
    public void after(){
        logger.info("----------before--");
    }
    @AfterReturning(value = "log()",returning = "resturl")
    public void AfterReturn(Object resturl){
        logger.info("-------AfterReturn----"+resturl);
    }
    @Data
    @AllArgsConstructor
    private class Retrul{
        private String url;
        private String ip;
        private String ClassMethod;
        private Object[] objects;
    }
}
