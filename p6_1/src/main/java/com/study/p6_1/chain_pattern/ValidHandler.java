package com.study.p6_1.chain_pattern;

import org.springframework.util.StringUtils;

public class ValidHandler extends Handler {
    private Handler next;
    @Override
    public void process(User user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassWord())) {
            System.out.println("用户名密码为空");
            return;
        }
        System.out.println("验证：" + user.getUserName());
        next.process(user);
    }

    @Override
    public void addNext(Handler handler) {
        this.next = handler;
    }
}
