package com.study.p6_1.factory_pattern.factorymethod;

import com.study.p6_1.factory_pattern.pay.Pay;
import com.study.p6_1.factory_pattern.pay.WechatPay;

public class WechatPayFactory implements PayFactory {
    @Override
    public Pay create() {
        return new WechatPay();
    }
}
