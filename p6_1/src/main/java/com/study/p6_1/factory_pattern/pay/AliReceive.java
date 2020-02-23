package com.study.p6_1.factory_pattern.pay;

public class AliReceive implements Receive {
    @Override
    public void receive() {
        System.out.println("支付宝收款");
    }
}
