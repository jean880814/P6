package com.study.p6_1.principle.srp;

/**
 * @program: P6
 * @author: Jean
 * @create: 2022-12-16 17:29
 */
public class LiveCourse {
    public void study(String courseName) {
        System.out.println(courseName + "不能快进");
    }

    public static void main(String[] args) {
        LiveCourse liveCourse = new LiveCourse();
        liveCourse.study("直播");

        ReplayCourse replayCourse = new ReplayCourse();
        replayCourse.study("录播");
    }
}
