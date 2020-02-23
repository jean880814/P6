package com.study.p6_1.p61_1_1.abstractfactory;

import com.study.p6_1.p61_1_1.pay.Pay;
import com.study.p6_1.p61_1_1.pay.Receive;

public class ApplePayFactory extends AbroadAbstractFactory {
    @Override
    protected void exchangeRate() {

    }

    @Override
    public Pay createPay() {
        return null;
    }

    @Override
    public Receive createReceive() {
        return null;
    }
}
