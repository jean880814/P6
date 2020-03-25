package com.study.testspringbootstarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix=FormatProperties.HELLO_FORMAT_PREFIX)
public class FormatProperties {
    public static final String HELLO_FORMAT_PREFIX="jean.format";
    private Map<String,Object> info;

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}
