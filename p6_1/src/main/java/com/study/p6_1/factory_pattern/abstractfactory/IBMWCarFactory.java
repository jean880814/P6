package com.study.p6_1.factory_pattern.abstractfactory;

import com.study.p6_1.factory_pattern.car.BMWCar;
import com.study.p6_1.factory_pattern.car.BMWCarCare;
import com.study.p6_1.factory_pattern.car.Car;
import com.study.p6_1.factory_pattern.car.CarCare;

public class IBMWCarFactory implements ICarFactory {
    @Override
    public Car create() {
        return new BMWCar();
    }

    @Override
    public CarCare createCare() {
        return new BMWCarCare();
    }
}
