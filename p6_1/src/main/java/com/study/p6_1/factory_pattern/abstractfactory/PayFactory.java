package com.study.p6_1.factory_pattern.abstractfactory;

import com.study.p6_1.factory_pattern.pay.Pay;
import com.study.p6_1.factory_pattern.pay.Receive;

public interface PayFactory {
    Pay createPay();
    Receive createReceive();
}
