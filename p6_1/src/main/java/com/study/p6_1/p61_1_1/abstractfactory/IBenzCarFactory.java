package com.study.p6_1.p61_1_1.abstractfactory;

import com.study.p6_1.p61_1_1.car.BenzCar;
import com.study.p6_1.p61_1_1.car.BenzCarCare;
import com.study.p6_1.p61_1_1.car.Car;
import com.study.p6_1.p61_1_1.car.CarCare;

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
