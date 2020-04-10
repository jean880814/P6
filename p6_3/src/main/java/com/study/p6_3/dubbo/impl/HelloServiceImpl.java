package com.study.p6_3.dubbo.impl;

import com.jean.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class HelloServiceImpl extends AbstractServiceImpl implements HelloService {
    public String say(String name) {
        return "helloService" + name;
    }
}
