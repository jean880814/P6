package com.study.p6_1.p61_1_1.factorymethod;

import com.study.p6_1.p61_1_1.pay.AliPay;
import com.study.p6_1.p61_1_1.pay.Pay;

public class AliPayFactory implements PayFactory {
    @Override
    public Pay create() {
        return new AliPay();
    }
}
