package com.study.p6_2.spring.framework.aop.aspect;

import com.study.p6_2.spring.framework.aop.intercept.MyJoinPoint;
import com.study.p6_2.spring.framework.aop.intercept.MyMethodInterceptor;
import com.study.p6_2.spring.framework.aop.intercept.MyReflectiveMethodInvocation;

import java.lang.reflect.Method;

public class MyAfterThrowingAdviceInterceptor extends MyAbstractAspectAdvice implements MyMethodInterceptor {
    private MyJoinPoint mi;
    public MyAfterThrowingAdviceInterceptor(Method aspectMethod, Object aspectObject) {
        super(aspectMethod, aspectObject);
    }

    @Override
    public Object invoke(MyReflectiveMethodInvocation invocation) throws Throwable {
        try{
            return invocation.proceed();
        } catch (Throwable e) {
            this.mi = invocation;
            throwing(invocation.getMethod(), e, invocation.getArguments(), invocation.getThis());
            throw e;
        }
    }
    private void throwing(Method method, Throwable e, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.mi, null, e);
    }


}
