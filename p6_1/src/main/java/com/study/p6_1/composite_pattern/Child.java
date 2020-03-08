package com.study.p6_1.composite_pattern;

public class Child extends BaseNode {
    public Child(String name) {
        super(name);
    }

    @Override
    protected void show() {
        System.out.println(this.name);
    }
}
