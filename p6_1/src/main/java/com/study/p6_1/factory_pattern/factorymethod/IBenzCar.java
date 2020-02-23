package com.study.p6_1.factory_pattern.factorymethod;

import com.study.p6_1.factory_pattern.car.BenzCar;
import com.study.p6_1.factory_pattern.car.Car;

public class IBenzCar implements ICar {
    @Override
    public Car create() {
        return new BenzCar();
    }
}
