package com.study.p6_2.p62_5.Jbatis.session;

import com.study.p6_2.p62_5.Jbatis.executor.JExecutor;

public class JSqlSession {
    private JExecutor executor;
    private JCoufiguration coufiguration;

    public  JSqlSession(JCoufiguration jCoufiguration){
        this.coufiguration = jCoufiguration;
        this.executor = jCoufiguration.newExecutor();
    }

    public JCoufiguration getConfiguration() {
        return this.coufiguration;
    }


    public <T> T getMapper(Class<T> clazz){
        return this.coufiguration.getMapper(clazz, this);
    }

    public <T> T selectOne(String statementId, Object[] paramter, Class pojo){
        String sql = getConfiguration().getMappedStatement(statementId);
        return this.executor.query(sql, paramter, pojo);
    }
}
