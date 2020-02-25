package com.study.p6_2.p62_5.Jbatis.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Entity {
    Class<?> value();
}
