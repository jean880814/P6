package com.study.p6_1.p61_1_1.factorymethod;

import com.study.p6_1.p61_1_1.pay.Pay;
import com.study.p6_1.p61_1_1.pay.WechatPay;

public class WechatPayFactory implements PayFactory {
    @Override
    public Pay create() {
        return new WechatPay();
    }
}
