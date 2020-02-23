package com.study.p6_1.factory_pattern.abstractfactory;

public abstract class AbroadAbstractFactory implements PayFactory {
    protected void init(){
        System.out.println("国外支付初始化");
    }
    //国外汇率转换
    protected abstract void exchangeRate();
}
