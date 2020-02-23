package com.study.p6_1.p61_1_1.simplefactory;

import com.study.p6_1.p61_1_1.pay.Pay;

public class PayBeanFactory {

    public static Pay create(Class<? extends Pay> clazz) throws IllegalAccessException, InstantiationException {
        if (clazz != null) {
            return clazz.newInstance();
        }
        return null;
    }
}
