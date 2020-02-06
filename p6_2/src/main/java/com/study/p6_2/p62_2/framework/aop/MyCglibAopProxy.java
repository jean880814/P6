package com.study.p6_2.p62_2.framework.aop;

import com.study.p6_2.p62_2.framework.aop.support.MyAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyCglibAopProxy implements MyAopProxy, InvocationHandler {
    private MyAdvisedSupport advisedSupport;
    public MyCglibAopProxy(MyAdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
