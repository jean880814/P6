package com.study.p6_6.p6_6_1_2;

import java.util.ArrayList;
import java.util.List;

public class HeepOverTest {
    private static List<Person> personList = new ArrayList<Person>();
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            personList.add(new Person());
            Thread.sleep(10);
        }
    }

}
