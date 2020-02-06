package com.study.p6_2.p62_2.framework.aop.aspect;

import com.study.p6_2.p62_2.framework.aop.intercept.MyJoinPoint;
import com.study.p6_2.p62_2.framework.aop.intercept.MyMethodInterceptor;
import com.study.p6_2.p62_2.framework.aop.intercept.MyReflectiveMethodInvocation;

import java.lang.reflect.Method;

public class MyAfterReturningAdviceInterceptor extends MyAbstractAspectAdvice implements MyMethodInterceptor {
    private MyJoinPoint mi;
    public MyAfterReturningAdviceInterceptor(Method aspectMethod, Object aspectObject) {
        super(aspectMethod, aspectObject);
    }

    @Override
    public Object invoke(MyReflectiveMethodInvocation invocation) throws Throwable {
        Object returnVal = invocation.proceed();
        this.mi = invocation;
        after(invocation.getMethod(), returnVal, invocation.getArguments(), invocation.getThis());
        return returnVal;
    }

    private void after(Method method, Object retuanVal, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.mi, retuanVal, null);
    }
}
