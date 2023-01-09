package com.study.p6_1.principle.srp;

/**
 * @program: P6
 * @author: Jean
 * @create: 2022-12-16 14:46
 */
public class Course {
    public void study(String courseName) {
        if ("直播".equals(courseName)) {
            System.out.println("不能快进");
        } else {
            System.out.println("可以任意来回播放");
        }
    }

    public static void main(String[] args) {
        Course course = new Course();
        course.study("直播");
        course.study("录播");
    }
}
