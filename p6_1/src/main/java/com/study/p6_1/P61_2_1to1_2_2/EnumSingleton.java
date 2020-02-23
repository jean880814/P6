package com.study.p6_1.P61_2_1to1_2_2;

import com.study.p6_1.factory_pattern.car.BMWCar;
import com.study.p6_1.factory_pattern.car.Car;

public enum EnumSingleton {
    ENUM_SINGLETON;
    private Car car;

    private EnumSingleton() {
        car = new BMWCar();
    }

    public Car getCar() {
        return car;
    }
}
