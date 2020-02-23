package com.study.p6_1.factory_pattern.simplefactory;

import com.study.p6_1.factory_pattern.car.Car;

public class CarBeanFactory {

    public static Car create(Class clazz) throws IllegalAccessException, InstantiationException {
        if (clazz != null) {
            return (Car) clazz.newInstance();
        }
        return null;
    }
}
