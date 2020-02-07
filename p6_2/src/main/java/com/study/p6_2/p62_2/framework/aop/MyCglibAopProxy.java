package com.study.p6_2.p62_2.framework.aop;

import com.study.p6_2.p62_2.framework.aop.intercept.MyReflectiveMethodInvocation;
import com.study.p6_2.p62_2.framework.aop.support.MyAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MyCglibAopProxy implements MyAopProxy, InvocationHandler {
    private MyAdvisedSupport advised;
    public MyCglibAopProxy(MyAdvisedSupport advisedSupport) {
        this.advised = advisedSupport;
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
        List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method);
        MyReflectiveMethodInvocation invocation = new MyReflectiveMethodInvocation(proxy, this.advised.getTarget(), method, args, this.advised.getTargetClass(), chain);
        return invocation.proceed();
    }
}
