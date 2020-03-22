package com.study.p6_1.interpreter_pattern.calculate;

public class NumInterpreter implements IArithmeticInterpreter {
    private int value;

    public NumInterpreter(int value) {
        this.value = value;
    }


    public int interpret() {
        return this.value;
    }
}