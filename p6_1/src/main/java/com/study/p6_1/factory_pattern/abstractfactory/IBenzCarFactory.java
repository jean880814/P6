package com.study.p6_1.factory_pattern.abstractfactory;

import com.study.p6_1.factory_pattern.car.BenzCar;
import com.study.p6_1.factory_pattern.car.BenzCarCare;
import com.study.p6_1.factory_pattern.car.Car;
import com.study.p6_1.factory_pattern.car.CarCare;

public class IBenzCarFactory implements ICarFactory {
    @Override
    public Car create() {
        return new BenzCar();
    }

    @Override
    public CarCare createCare() {
        return new BenzCarCare();
    }
}
