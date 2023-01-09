package com.study.p6_1.principle.lkp;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: P6
 * @author: Jean
 * @create: 2022-12-19 17:15
 */
public class Employee {
    public void checkNumberOfCourses() {
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            courseList.add(new Course());
        }
        System.out.println("目前已发布的课程数量是" + courseList.size());
    }
}
