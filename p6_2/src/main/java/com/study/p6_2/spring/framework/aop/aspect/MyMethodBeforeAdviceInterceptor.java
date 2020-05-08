package com.study.p6_2.spring.framework.aop.aspect;

import com.study.p6_2.spring.framework.aop.intercept.MyJoinPoint;
import com.study.p6_2.spring.framework.aop.intercept.MyMethodInterceptor;
import com.study.p6_2.spring.framework.aop.intercept.MyReflectiveMethodInvocation;

import java.lang.reflect.Method;

public class MyMethodBeforeAdviceInterceptor extends MyAbstractAspectAdvice implements MyMethodInterceptor {
    private MyJoinPoint mi;

    public MyMethodBeforeAdviceInterceptor(Method aspectMethod, Object aspectObject) {
        super(aspectMethod, aspectObject);
    }

    @Override
    public Object invoke(MyReflectiveMethodInvocation invocation) throws Throwable {
        this.mi = invocation;
        before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.proceed();
    }

    private void before(Method method, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.mi, null, null);
    }
}
