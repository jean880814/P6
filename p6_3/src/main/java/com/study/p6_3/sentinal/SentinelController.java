package com.study.p6_3.sentinal;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @SentinelResource(value = "sayHello") //针对方法级别的限流
    @GetMapping("/say")
    public String sayHello(){
        System.out.println("hello world");
        return "hello world";
    }

}
