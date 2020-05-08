package com.study.p6_2.mybatis.Jbatis.executor;

public class JSimpleExecutor implements JExecutor {
    @Override
    public <T> T query(String sql, Object[] paramter, Class pojo) {
        JStatementHandler jStatementHandler = new JStatementHandler();
        return jStatementHandler.execute(sql, paramter, pojo);
    }
}
