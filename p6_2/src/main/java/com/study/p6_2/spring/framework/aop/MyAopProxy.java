package com.study.p6_2.spring.framework.aop;

public interface MyAopProxy {
    Object getProxy();
    Object getProxy(ClassLoader classLoader);
}
