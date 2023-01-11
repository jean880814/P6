package com.study.p6_1.principle.carp;

/**
 * @program: P6
 * @author: Jean
 * @create: 2023-01-10 16:15
 */
public class OracleConnection extends DBAbstractConnection {

    @Override
    public String getConnection() {
        return "Oracle数据库连接";
    }
}
