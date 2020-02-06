package com.study.p6_2.p62_2.framework.aop.intercept;

import java.lang.reflect.Method;

public interface MyJoinPoint {

    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);
}
