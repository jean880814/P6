package com.study.p6_1.template_pattern;

public abstract class Template {

    public abstract void doSomething();

    public void print() {
        System.out.println("print");
    }

    public final void execute() {
        print();
        doSomething();
    }
}