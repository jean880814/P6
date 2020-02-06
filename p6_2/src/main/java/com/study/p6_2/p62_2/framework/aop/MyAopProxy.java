package com.study.p6_2.p62_2.framework.aop;

public interface MyAopProxy {
    Object getProxy();
    Object getProxy(ClassLoader classLoader);
}
