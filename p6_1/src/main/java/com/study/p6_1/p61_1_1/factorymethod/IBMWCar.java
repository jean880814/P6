package com.study.p6_1.p61_1_1.factorymethod;

import com.study.p6_1.p61_1_1.car.BMWCar;
import com.study.p6_1.p61_1_1.car.Car;

public class IBMWCar implements ICar {
    @Override
    public Car create() {
        return new BMWCar();
    }
}
