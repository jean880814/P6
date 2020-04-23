package com.study.p6_3.nacos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@NacosPropertySource(dataId = "nacosController-nacos-config", groupId = "p6-3", autoRefreshed = true)
public class NacosController {

//    @NacosValue(value = "${nacos.test.propertie:123}", autoRefreshed = true)
    private String testProperties;

    @GetMapping("/nacos")
    public String nacos(){
        return testProperties;
    }
}
