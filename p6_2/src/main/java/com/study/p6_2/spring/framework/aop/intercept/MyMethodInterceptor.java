package com.study.p6_2.spring.framework.aop.intercept;

public interface MyMethodInterceptor {
    Object invoke(MyReflectiveMethodInvocation invocation) throws Throwable;
}
