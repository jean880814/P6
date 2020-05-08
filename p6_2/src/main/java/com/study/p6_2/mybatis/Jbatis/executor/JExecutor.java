package com.study.p6_2.mybatis.Jbatis.executor;

public interface JExecutor {
    <T> T query(String sql, Object[] paramter, Class pojo);
}
