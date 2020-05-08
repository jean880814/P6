package com.study.p6_2.mybatis.Jbatis.binding;

import com.study.p6_2.mybatis.Jbatis.session.JSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JMapperProxy implements InvocationHandler {
    private JSqlSession sqlSession;
    private Class pojo;

    public JMapperProxy(JSqlSession sqlSession, Class pojo) {
        this.sqlSession = sqlSession;
        this.pojo = pojo;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String statementId = method.getDeclaringClass().getName() + method.getName();

        return sqlSession.selectOne(statementId, args, this.pojo);
    }
}
