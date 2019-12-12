package com.study.p6_6.p6_6_1_2;

import java.util.ArrayList;
import java.util.List;

public class MatespaceOverTest {
    private static List<Class<?>> list = new ArrayList<Class<?>>();
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            list.addAll(MetaspaceUtil.createClasses());
            Thread.sleep(10);
        }
    }

}
