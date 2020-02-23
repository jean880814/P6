package com.study.p6_1.p61_1_1.factorymethod;

import com.study.p6_1.p61_1_1.pay.Pay;
import com.study.p6_1.p61_1_1.pay.UnionPay;

public class UnionPayFactory implements PayFactory {
    @Override
    public Pay create() {
        return new UnionPay();
    }
}
