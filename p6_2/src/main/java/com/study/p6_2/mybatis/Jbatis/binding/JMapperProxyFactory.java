package com.study.p6_2.mybatis.Jbatis.binding;

import com.study.p6_2.mybatis.Jbatis.session.JSqlSession;

import java.lang.reflect.Proxy;

public class JMapperProxyFactory<T> {
    private Class<T> mapper;
    private Class pojo;

    public JMapperProxyFactory(Class<T> mapper, Class pojo) {
        this.mapper = mapper;
        this.pojo = pojo;
    }

    public T newInstance(JSqlSession sqlSession){
        return (T) Proxy.newProxyInstance(mapper.getClassLoader(), new Class[]{mapper}, new JMapperProxy(sqlSession, pojo));
    }
}
