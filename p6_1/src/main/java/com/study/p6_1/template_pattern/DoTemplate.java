package com.study.p6_1.template_pattern;

public class DoTemplate {
    public void doA(){
        new Template() {
            @Override
            public void doSomething() {

            }
        }.execute();
    }
}
