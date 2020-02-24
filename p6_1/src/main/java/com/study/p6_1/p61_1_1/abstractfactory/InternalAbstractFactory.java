package com.study.p6_1.p61_1_1.abstractfactory;

public abstract class InternalAbstractFactory implements PayFactory {
    protected void init(){
        System.out.println("国内支付初始化");
    }

    //国内支付行内转账手续费
    protected abstract void serviceCharge();

}
