package com.study.p6_1.factory_pattern.pay;

public class WechatReceive implements Receive {
    @Override
    public void receive() {
        System.out.println("微信收款");
    }
}
