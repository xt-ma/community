package com.nowcoder.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AlphaAspect {
    // advice的执行顺序: https://blog.csdn.net/qq_32331073/article/details/80596084

    // 定义连接点Pointcut
    // 所有返回值 service包下 所有组件 所有方法 所有参数
    // * com.nowcoder.community.service.*.*(..)
    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("after");
    }

    //
    @AfterReturning("pointcut()")
    public void afterRetuning() {
        System.out.println("afterRetuning");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before");
        // 调用目标组件，接收返回值
        Object obj = joinPoint.proceed();
        System.out.println("around after");
        return obj;
    }

}
