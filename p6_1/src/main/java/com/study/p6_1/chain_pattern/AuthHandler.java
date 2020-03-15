package com.study.p6_1.chain_pattern;

public class AuthHandler extends Handler {
    @Override
    public void process(User user) {
        if (user.getRole().equals("admin")) {
            System.out.println("你好，管理员");
        } else {
            System.out.println("你好，您不是管理员");
        }
    }

    @Override
    public void addNext(Handler handler) {

    }
}
