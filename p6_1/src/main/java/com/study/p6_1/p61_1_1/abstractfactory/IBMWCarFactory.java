package com.study.p6_1.p61_1_1.abstractfactory;

import com.study.p6_1.p61_1_1.car.BMWCar;
import com.study.p6_1.p61_1_1.car.BMWCarCare;
import com.study.p6_1.p61_1_1.car.Car;
import com.study.p6_1.p61_1_1.car.CarCare;

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
