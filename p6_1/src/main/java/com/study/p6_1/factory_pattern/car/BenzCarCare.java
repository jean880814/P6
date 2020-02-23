package com.study.p6_1.factory_pattern.car;

public class BenzCarCare implements CarCare {
    @Override
    public void care() {
        System.out.println("保养奔驰");
    }
}
