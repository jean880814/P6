package com.study.p6_1.factory_pattern.factorymethod;

import com.study.p6_1.factory_pattern.pay.AliPay;
import com.study.p6_1.factory_pattern.pay.Pay;

public class AliPayFactory implements PayFactory {
    @Override
    public Pay create() {
        return new AliPay();
    }
}
