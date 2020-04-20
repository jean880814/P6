package com.study.p6_3.sentinal;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: P6
 * @description:
 * @author: Jean
 * @create: 2020-04-13 20:00
 */
@Configuration
public class AopConfiguration {
    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }
}
