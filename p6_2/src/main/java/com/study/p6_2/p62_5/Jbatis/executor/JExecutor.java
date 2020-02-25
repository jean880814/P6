package com.study.p6_2.p62_5.Jbatis.executor;

public interface JExecutor {
    <T> T query(String sql, Object[] paramter, Class pojo);
}
