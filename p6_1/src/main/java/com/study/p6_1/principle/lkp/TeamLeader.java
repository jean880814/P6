package com.study.p6_1.principle.lkp;

/**
 * @program: P6
 * @author: Jean
 * @create: 2022-12-19 17:17
 */
public class TeamLeader {
    public void commandCheckNumber(Employee employee) {
        employee.checkNumberOfCourses();
    }

    public static void main(String[] args) {
        TeamLeader teamLeader = new TeamLeader();
        Employee employee = new Employee();
        teamLeader.commandCheckNumber(employee);
    }
}
