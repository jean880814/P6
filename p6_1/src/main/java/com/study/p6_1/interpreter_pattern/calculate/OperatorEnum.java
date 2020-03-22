package com.study.p6_1.interpreter_pattern.calculate;

public enum OperatorEnum {
    LEFT_BRACKET("("),
    RIGHT_BRACKET(")"),
    SUB("-"),
    ADD("+"),
    MULTI("*"),
    DIV("/"),
    ;
    private String operator;

    public String getOperator() {
        return operator;
    }

    OperatorEnum(String operator) {
        this.operator = operator;
    }
}
