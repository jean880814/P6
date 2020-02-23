package com.study.p6_1.P61_2_1to1_2_2;

import com.study.p6_1.p61_1_1.car.BMWCar;
import com.study.p6_1.p61_1_1.car.Car;

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
