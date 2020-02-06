package com.study.p6_2.p62_2.framework.aop.aspect;

import com.study.p6_2.p62_2.framework.aop.intercept.MyJoinPoint;

import java.lang.reflect.Method;

public abstract class MyAbstractAspectAdvice {
    private Method aspectMethod;
    private Object aspectObject;

    public MyAbstractAspectAdvice(Method aspectMethod, Object aspectObject) {
        this.aspectMethod = aspectMethod;
        this.aspectObject = aspectObject;
    }

    protected Object invokeAdviceMethod(
            MyJoinPoint myJoinPoint, Object returnValue, Throwable ex)
            throws Throwable {
        Class<?>[] paramTypes = this.aspectMethod.getParameterTypes();
        if (null == paramTypes || paramTypes.length == 0) {
            return this.aspectMethod.invoke(aspectObject);
        } else {
            Object[] args = new Object[paramTypes.length];
            for (int i = 0; i < paramTypes.length; i++) {
                if (paramTypes[i] == MyJoinPoint.class) {
                    args[i] = myJoinPoint;
                } else if (paramTypes[i] == Throwable.class) {
                    args[i] = ex;
                } else if (paramTypes[i] == Object.class) {
                    args[i] = returnValue;
                }
            }
            return this.aspectMethod.invoke(aspectObject, args);
        }
    }

}
