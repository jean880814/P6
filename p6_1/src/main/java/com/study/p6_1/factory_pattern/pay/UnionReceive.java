package com.study.p6_1.factory_pattern.pay;

public class UnionReceive implements Receive {
    @Override
    public void receive() {
        System.out.println("银联收款");
    }
}
