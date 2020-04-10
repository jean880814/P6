package com.study.p6_3.dubbo.impl;

public abstract class AbstractServiceImpl {
    public String doParent(){
        System.out.println("do parent");
        return "parent";
    }
}
