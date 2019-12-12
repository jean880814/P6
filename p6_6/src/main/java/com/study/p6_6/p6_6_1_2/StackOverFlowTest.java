package com.study.p6_6.p6_6_1_2;

public class StackOverFlowTest {
    private static int count;
    public static void main(String[] args) {
        stack(0);
    }

    public static void stack(int i){
        System.out.println(count ++);
        stack(count);
    }
}
