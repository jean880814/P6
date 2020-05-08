package com.study.p6_2.spring.demo.aspect;

import com.study.p6_2.spring.framework.aop.intercept.MyJoinPoint;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class LogAspect {

    //在调用一个方法之前，执行before方法
    public void before(MyJoinPoint joinPoint){
        joinPoint.setUserAttribute("startTime_" + joinPoint.getMethod().getName(),System.currentTimeMillis());
        //这个方法中的逻辑，是由我们自己写的
        log.info("Invoker Before Method!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()));
    }

    //在调用一个方法之后，执行after方法
    public void after(MyJoinPoint joinPoint){
        log.info("Invoker After Method!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()));
        long startTime = (Long) joinPoint.getUserAttribute("startTime_" + joinPoint.getMethod().getName());
        long endTime = System.currentTimeMillis();
        System.out.println("use time :" + (endTime - startTime));
    }

    public void afterThrowing(MyJoinPoint joinPoint, Throwable ex){
        log.info("出现异常" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()) +
                "\nThrows:" + ex.getMessage());
    }

    public Object around(MyJoinPoint joinPoint){
        Object returnVal = null;
        log.info("环绕通知前置!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()));
        try {
            returnVal = joinPoint.proceed();
            log.info("环绕通知返回" + returnVal);
        } catch (Throwable ex) {
            log.info("出现异常" +
                    "\nTargetObject:" +  joinPoint.getThis() +
                    "\nArgs:" + Arrays.toString(joinPoint.getArguments()) +
                    "\nThrows:" + ex.getMessage());
        }
        log.info("环绕通知后置!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()));
        return returnVal;
    }

}
