package com.study.p6_2.spring.framework.aop.intercept;

import java.lang.reflect.Method;

public interface MyJoinPoint {

    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);

    Object proceed() throws Throwable;
}
