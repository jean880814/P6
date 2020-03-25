package com.study.testspringbootstarter.service;

import com.alibaba.fastjson.JSON;

public class JsonProcessor implements MyProcessor {

    @Override
    public <T> String format(T o) {
        return "JSON format:" + JSON.toJSONString(o);
    }
}
