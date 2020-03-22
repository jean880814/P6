package com.study.p6_1.interpreter_pattern.calculate;

/**
 * Created by Tom.
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("result: " + new GPCalculator("100*(2+40)*1+66").calculate());
    }

}
