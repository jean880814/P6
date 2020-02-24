package com.study.p6_1.factory_pattern.factorymethod;

import com.study.p6_1.factory_pattern.pay.Pay;
import com.study.p6_1.factory_pattern.pay.UnionPay;

public class UnionPayFactory implements PayFactory {
    @Override
    public Pay create() {
        return new UnionPay();
    }
}
