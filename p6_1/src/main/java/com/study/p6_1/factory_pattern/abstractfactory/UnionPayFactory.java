package com.study.p6_1.factory_pattern.abstractfactory;

import com.study.p6_1.factory_pattern.pay.Pay;
import com.study.p6_1.factory_pattern.pay.Receive;
import com.study.p6_1.factory_pattern.pay.UnionPay;
import com.study.p6_1.factory_pattern.pay.UnionReceive;

public class UnionPayFactory extends InternalAbstractFactory {
    @Override
    protected void serviceCharge() {
        System.out.println("银联手续费");
    }

    @Override
    public Pay createPay() {
        super.init();
        return new UnionPay();
    }

    @Override
    public Receive createReceive() {
        return new UnionReceive();
    }
}
