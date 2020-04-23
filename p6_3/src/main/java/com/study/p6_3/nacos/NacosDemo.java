package com.study.p6_3.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;

public class NacosDemo {
    public static void main(String[] args) throws NacosException {
        Properties properties = new Properties();
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content=configService.getConfig("", "",3000);

    }
}
