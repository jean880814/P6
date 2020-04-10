package com.demo.dubbo.p6_3_consumer.controller;

import com.jean.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DubboConsumerTest2Controller {
    @Reference
    HelloService helloService;

    @GetMapping("/test2")
    public String test(){
        return helloService.say("jean");
    }
}
