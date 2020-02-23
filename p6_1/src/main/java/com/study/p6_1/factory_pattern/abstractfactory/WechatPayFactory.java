package com.study.p6_1.factory_pattern.abstractfactory;

import com.study.p6_1.factory_pattern.pay.Pay;
import com.study.p6_1.factory_pattern.pay.Receive;
import com.study.p6_1.factory_pattern.pay.WechatPay;
import com.study.p6_1.factory_pattern.pay.WechatReceive;

public class WechatPayFactory extends InternalAbstractFactory {
    @Override
    protected void serviceCharge() {
        System.out.println("微信手续费");
    }

    @Override
    public Pay createPay() {
        super.init();
        return new WechatPay();
    }

    @Override
    public Receive createReceive() {
        return new WechatReceive();
    }
}
