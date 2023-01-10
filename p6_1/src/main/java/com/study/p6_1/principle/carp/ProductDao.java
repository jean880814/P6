package com.study.p6_1.principle.carp;

/**
 * @program: P6
 * @author: Jean
 * @create: 2023-01-10 15:00
 */
public class ProductDao {
    private DBConnection dbConn;
    public void setDBConnection(DBConnection dbConn){
        this.dbConn = dbConn;
    }

    public void addProduct(){
        String conn = dbConn.getConnection();
        System.out.println("使用" + conn + "增加产品");
    }
}
