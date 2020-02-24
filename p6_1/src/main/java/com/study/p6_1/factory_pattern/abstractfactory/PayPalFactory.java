package com.study.p6_1.factory_pattern.abstractfactory;

import com.study.p6_1.factory_pattern.pay.Pay;
import com.study.p6_1.factory_pattern.pay.Receive;

public class PayPalFactory extends AbroadAbstractFactory {
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
