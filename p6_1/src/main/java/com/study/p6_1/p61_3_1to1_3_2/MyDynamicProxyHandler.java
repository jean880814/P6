package com.study.p6_1.p61_3_1to1_3_2;

import java.lang.reflect.Method;

public class MyDynamicProxyHandler implements MyInvocationHandler {

    private Object target;

    public Object getInstance(Object target) throws ClassNotFoundException {
        this.target = target;
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return MyDynamicProxy.newProxyInstance(new MyClassLoader(), interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(this.target, args);
        after();
        return invoke;
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }
}
