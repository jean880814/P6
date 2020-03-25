package com.study.testspringbootstarter.service;

import java.util.Objects;

public class StringProcessor implements MyProcessor {
    @Override
    public <T> String format(T o) {
        return "String format:" + Objects.toString(o);
    }
}
