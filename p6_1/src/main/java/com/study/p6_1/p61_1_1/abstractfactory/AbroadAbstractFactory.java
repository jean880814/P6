package com.study.p6_1.p61_1_1.abstractfactory;

public abstract class AbroadAbstractFactory implements PayFactory {
    protected void init(){
        System.out.println("国外支付初始化");
    }
    //国外汇率转换
    protected abstract void exchangeRate();
}
