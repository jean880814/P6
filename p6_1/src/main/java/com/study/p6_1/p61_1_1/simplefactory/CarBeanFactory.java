package com.study.p6_1.p61_1_1.simplefactory;

import com.study.p6_1.p61_1_1.car.Car;

public class CarBeanFactory {

    public static Car create(Class clazz) throws IllegalAccessException, InstantiationException {
        if (clazz != null) {
            return (Car) clazz.newInstance();
        }
        return null;
    }
}
