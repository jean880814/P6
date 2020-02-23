package com.study.p6_1.factory_pattern.factorymethod;

import com.study.p6_1.factory_pattern.car.BMWCar;
import com.study.p6_1.factory_pattern.car.Car;

public class IBMWCar implements ICar {
    @Override
    public Car create() {
        return new BMWCar();
    }
}
