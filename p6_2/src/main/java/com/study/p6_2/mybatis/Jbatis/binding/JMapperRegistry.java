package com.study.p6_2.mybatis.Jbatis.binding;

import com.study.p6_2.mybatis.Jbatis.session.JSqlSession;

import java.util.HashMap;
import java.util.Map;

public class JMapperRegistry {
    private Map<Class<?>, JMapperProxyFactory> proxyFactoryMap = new HashMap<>();

    public void addMapper(Class<?> mapper, Class pojo){
        proxyFactoryMap.put(mapper, new JMapperProxyFactory(mapper, pojo));
    }

    public <T> T getMapper(Class<T> clazz, JSqlSession sqlSession) {
        JMapperProxyFactory proxyFactory = proxyFactoryMap.get(clazz);
        if (proxyFactory == null) {
            throw new RuntimeException("Type: " + clazz + " can not find");
        }
        return (T)proxyFactory.newInstance(sqlSession);
    }
}
