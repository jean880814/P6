package com.study.p6_2.mybatis.typehandle;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: P6
 * @author: Jean
 * @create: 2020-05-08 21:24
 */
public class MyTypeHandle implements TypeHandler<List<Integer>> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<Integer> integers, JdbcType jdbcType) throws SQLException {
        StringBuffer sb = new StringBuffer();
        integers.forEach(integer -> {
            sb.append(integer).append(",");
        });
        preparedStatement.setString(i, sb.toString().substring(0,sb.toString().length() - 1));
    }

    @Override
    public List<Integer> getResult(ResultSet resultSet, String s) throws SQLException {
        String[] arr = resultSet.getString(s).split(",");
        int[] ints = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
        return Arrays.stream(ints).boxed().collect(Collectors.toList());
    }

    @Override
    public List<Integer> getResult(ResultSet resultSet, int i) throws SQLException {
        String[] arr = resultSet.getString(i).split(",");
        int[] ints = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
        return Arrays.stream(ints).boxed().collect(Collectors.toList());
    }

    @Override
    public List<Integer> getResult(CallableStatement callableStatement, int i) throws SQLException {
        String[] arr = callableStatement.getString(i).split(",");
        int[] ints = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
        return Arrays.stream(ints).boxed().collect(Collectors.toList());
    }
}
