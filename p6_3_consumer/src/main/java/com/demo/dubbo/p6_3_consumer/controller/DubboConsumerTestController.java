package com.demo.dubbo.p6_3_consumer.controller;

import com.jean.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DubboConsumerTestController {
    @Reference
    UserService userService;

    @GetMapping("/test")
    public String test(){
        return userService.doParent();
    }
}
