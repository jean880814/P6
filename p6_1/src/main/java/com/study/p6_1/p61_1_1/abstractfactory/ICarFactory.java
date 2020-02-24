package com.study.p6_1.p61_1_1.abstractfactory;

import com.study.p6_1.p61_1_1.car.Car;
import com.study.p6_1.p61_1_1.car.CarCare;

public interface ICarFactory {
    Car create();

    CarCare createCare();
}
