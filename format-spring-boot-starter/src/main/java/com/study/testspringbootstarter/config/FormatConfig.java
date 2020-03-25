package com.study.testspringbootstarter.config;

import com.study.testspringbootstarter.service.FormatTemplate;
import com.study.testspringbootstarter.service.JsonProcessor;
import com.study.testspringbootstarter.service.MyProcessor;
import com.study.testspringbootstarter.service.StringProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties(FormatProperties.class)
public class FormatConfig {

    @ConditionalOnMissingClass("com.alibaba.fastjson.JSON")
    @Primary
    @Bean
    public MyProcessor stringProcesser(){
        return new StringProcessor();
    }

    @ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
    @Bean
    public MyProcessor jsonProcesser(){
        return new JsonProcessor();
    }

    @Bean
    public FormatTemplate formatTemplate(MyProcessor formatProcessor, FormatProperties formatProperties){
        return new FormatTemplate(formatProcessor, formatProperties);
    }
}
