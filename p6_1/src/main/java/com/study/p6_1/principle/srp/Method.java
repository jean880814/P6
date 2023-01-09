package com.study.p6_1.principle.srp;

/**
 * @program: P6
 * @author: Jean
 * @create: 2022-12-16 17:50
 */
public class Method {
    public void modifyUserInfo(String username, String address) {
        username = "zhangsan";
        address = "xiamen";
    }

    public void modifyUserInfo(String username, String address, boolean bool) {
        if (bool) {

        } else {

        }
        username = "zhangsan";
        address = "xiamen";
    }

    private void modifyUserName(String username) {
        username = "zhangsan";
    }
    private void modifyAddress(String address) {
        address = "xiamen";
    }
}
