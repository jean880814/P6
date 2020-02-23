package com.study.p6_1.factory_pattern.abstractfactory;

import com.study.p6_1.factory_pattern.pay.AliPay;
import com.study.p6_1.factory_pattern.pay.AliReceive;
import com.study.p6_1.factory_pattern.pay.Pay;
import com.study.p6_1.factory_pattern.pay.Receive;

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
