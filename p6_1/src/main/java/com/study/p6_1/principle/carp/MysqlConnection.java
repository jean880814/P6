package com.study.p6_1.principle.carp;

/**
 * @program: P6
 * @author: Jean
 * @create: 2023-01-10 16:07
 */
public class MysqlConnection extends DBAbstractConnection {

    @Override
    public String getConnection() {
        return "Mysql数据库连接";
    }
}
