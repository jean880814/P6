package com.study.p6_1.factory_pattern.abstractfactory;

import com.study.p6_1.factory_pattern.car.Car;
import com.study.p6_1.factory_pattern.car.CarCare;

public interface ICarFactory {
    Car create();

    CarCare createCare();
}
