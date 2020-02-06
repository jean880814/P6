package com.study.p6_2.p62_2.framework.beans.support;

import com.study.p6_2.p62_2.framework.beans.config.MyBeanDefinition;
import com.study.p6_2.p62_2.framework.context.support.MyAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyDefaultListableBeanFactory extends MyAbstractApplicationContext {
    //存储注册信息的BeanDefinition
    protected final Map<String, MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
}
