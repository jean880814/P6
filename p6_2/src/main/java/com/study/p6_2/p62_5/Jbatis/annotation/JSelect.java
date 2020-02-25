package com.study.p6_2.p62_5.Jbatis.annotation;

import java.lang.annotation.*;

/**
 * 注解方法，配置SQL语句
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface JSelect {
    String value();
}
