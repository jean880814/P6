package com.study.p6_1.p61_1_1.abstractfactory;

import com.study.p6_1.p61_1_1.pay.Pay;
import com.study.p6_1.p61_1_1.pay.Receive;

public interface PayFactory {
    Pay createPay();
    Receive createReceive();
}
