package com.study.p6_1.factory_pattern.car;

public class BMWCarCare implements CarCare {
    @Override
    public void care() {
        System.out.println("保养宝马");
    }
}
