package com.study.p6_1.principle.dip;

/**
 * @program: P6
 * @author: Jean
 * @create: 2022-12-06 18:30
 */
public class ZhangSan {
    public void study(ICourse iCourse) {
        iCourse.study();
    }

    public static void main(String[] args) {
        ZhangSan zhangsan = new ZhangSan();
        zhangsan.study(new JavaCourse());
        zhangsan.study(new PythonCourse());
    }
}
