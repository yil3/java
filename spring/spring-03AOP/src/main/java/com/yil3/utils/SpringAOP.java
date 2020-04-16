package com.yil3.utils;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class SpringAOP {
    @Before("execution(public int com.yil3.utils.CalImpl.*(..))")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println(name+"的方法参数是"+args);
    }

    @After("execution(public int com.yil3.utils.CalImpl.*(..))")
    public void after (JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法执行完毕");
    }

    @AfterReturning(value = "execution(public int com.yil3.utils.CalImpl.*(..))",returning = "res")
    public void afterReturn(JoinPoint joinPoint, Object res){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法的结果是"+res);
        System.out.println("-------------------------");
    }
}
