package com.study.p6_2.spring.framework.aop.config;

import lombok.Data;

@Data
public class MyAopConfig {
    private String pointCut;
    private String aspectClass;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;
    private String aspectAround;
}
