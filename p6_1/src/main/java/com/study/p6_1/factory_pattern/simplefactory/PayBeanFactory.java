package com.study.p6_1.factory_pattern.simplefactory;

import com.study.p6_1.factory_pattern.pay.Pay;

public class PayBeanFactory {

    public static Pay create(Class<? extends Pay> clazz) throws IllegalAccessException, InstantiationException {
        if (clazz != null) {
            return clazz.newInstance();
        }
        return null;
    }
}
