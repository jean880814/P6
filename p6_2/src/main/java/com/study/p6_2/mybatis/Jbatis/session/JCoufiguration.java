package com.study.p6_2.mybatis.Jbatis.session;

import com.study.p6_2.mybatis.Jbatis.binding.JMapperRegistry;
import com.study.p6_2.mybatis.Jbatis.executor.JExecutor;
import com.study.p6_2.mybatis.Jbatis.executor.JSimpleExecutor;

import java.util.HashMap;
import java.util.Map;

public class JCoufiguration {
    private JMapperRegistry mapperRegistry;
    private Map<String, String> mappedStatement = new HashMap<>();
    public JExecutor newExecutor() {
        return new JSimpleExecutor();
    }

    public JCoufiguration() {
    }

    public <T> T getMapper(Class<T> clazz, JSqlSession jSqlSession) {
        return mapperRegistry.getMapper(clazz, jSqlSession);
    }

    public String getMappedStatement(String statementId) {
        return mappedStatement.get(statementId);
    }
}
