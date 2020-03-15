package com.study.p6_1.chain_pattern;

public class LoginHandler extends Handler {
    private Handler next;
    @Override
    public void process(User user) {
        System.out.println("登录成功");
        user.setRole("admin");
        next.process(user);
    }

    @Override
    public void addNext(Handler handler) {
        this.next = handler;
    }
}
