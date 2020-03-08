package com.study.p6_1.composite_pattern;

public abstract class BaseNode {
    protected String name;
    public BaseNode(String name){
        this.name = name;
    }
    protected abstract void show();
}
