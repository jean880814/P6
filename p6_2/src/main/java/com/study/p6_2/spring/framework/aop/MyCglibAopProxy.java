package com.study.p6_2.spring.framework.aop;

import com.study.p6_2.spring.framework.aop.intercept.MyReflectiveMethodInvocation;
import com.study.p6_2.spring.framework.aop.support.MyAdvisedSupport;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

public class MyCglibAopProxy implements MyAopProxy, MethodInterceptor {
    private MyAdvisedSupport advised;
    public MyCglibAopProxy(MyAdvisedSupport advisedSupport) {
        this.advised = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.advised.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(this.advised.getTargetClass());
        enhancer.setCallback((Callback) this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method);
        MyReflectiveMethodInvocation invocation = new MyReflectiveMethodInvocation(methodProxy, this.advised.getTarget(), method, objects, this.advised.getTargetClass(), chain);
        return invocation.proceed();
    }
}
