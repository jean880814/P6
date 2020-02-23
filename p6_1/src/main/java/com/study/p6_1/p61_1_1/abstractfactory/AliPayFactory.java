package com.study.p6_1.p61_1_1.abstractfactory;

import com.study.p6_1.p61_1_1.pay.AliPay;
import com.study.p6_1.p61_1_1.pay.AliReceive;
import com.study.p6_1.p61_1_1.pay.Pay;
import com.study.p6_1.p61_1_1.pay.Receive;

public class AliPayFactory extends InternalAbstractFactory {
    @Override
    protected void serviceCharge() {
        System.out.println("支付宝手续费");
    }

    @Override
    public Pay createPay() {
        super.init();
        return new AliPay();
    }

    @Override
    public Receive createReceive() {
        return new AliReceive();
    }
}
