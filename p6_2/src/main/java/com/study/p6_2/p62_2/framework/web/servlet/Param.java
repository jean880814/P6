package com.study.p6_2.p62_2.framework.web.servlet;

public class Param {
    String name;
    int index;
    Class<?> type;

    public Param(String name, int index, Class<?> type) {
        this.name = name;
        this.index = index;
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
