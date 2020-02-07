package com.study.p6_2.p62_2.framework.aop.aspect;

import com.study.p6_2.p62_2.framework.aop.intercept.MyMethodInterceptor;
import com.study.p6_2.p62_2.framework.aop.intercept.MyReflectiveMethodInvocation;

import java.lang.reflect.Method;

public class MyAroundAdviceInterceptor extends MyAbstractAspectAdvice implements MyMethodInterceptor {
    public MyAroundAdviceInterceptor(Method aspectMethod, Object aspectObject) {
        super(aspectMethod, aspectObject);
    }

    @Override
    public Object invoke(MyReflectiveMethodInvocation invocation) throws Throwable {
        return super.invokeAdviceMethod(invocation, null, null);
    }
}
