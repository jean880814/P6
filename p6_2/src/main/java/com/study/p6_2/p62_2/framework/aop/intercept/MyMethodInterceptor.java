package com.study.p6_2.p62_2.framework.aop.intercept;

public interface MyMethodInterceptor {
    Object invoke(MyReflectiveMethodInvocation invocation) throws Throwable;
}
