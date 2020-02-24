package com.study.p6_1.factory_pattern.pay;

public class UnionPay implements Pay {
    @Override
    public void pay() {
        System.out.println("银联支付");
    }
}
