package com.study.p6_1.p61_1_1.pay;

public class WechatPay implements Pay {
    @Override
    public void pay() {
        System.out.println("微信支付");
    }
}
