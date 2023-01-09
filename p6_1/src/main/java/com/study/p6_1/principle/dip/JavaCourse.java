package com.study.p6_1.principle.dip;

/**
 * @program: P6
 * @author: Jean
 * @create: 2022-12-06 17:14
 */
public class JavaCourse implements ICourse {

    @Override
    public void study() {
        System.out.println("张三在学习java课程");
    }
}
