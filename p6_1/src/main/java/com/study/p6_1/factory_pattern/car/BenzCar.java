package com.study.p6_1.factory_pattern.car;

public class BenzCar implements Car {

    @Override
    public void drive() {
        System.out.println("驾驶奔驰");
    }
}
