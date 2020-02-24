package com.study.p6_1.factory_pattern.pay;

public class AliPay implements Pay {
    @Override
    public void pay() {
        System.out.println("支付宝支付");
    }
}
