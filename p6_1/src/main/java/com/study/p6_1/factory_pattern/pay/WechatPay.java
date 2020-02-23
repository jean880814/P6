package com.study.p6_1.factory_pattern.pay;

public class WechatPay implements Pay {
    @Override
    public void pay() {
        System.out.println("微信支付");
    }
}
