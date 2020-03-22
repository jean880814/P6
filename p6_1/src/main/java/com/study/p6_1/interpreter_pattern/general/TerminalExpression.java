package com.study.p6_1.interpreter_pattern.general;

// 终结符表达式
public class TerminalExpression implements IExpression {

    private Object value;

    public Object interpret(Context context) {
        // 实现文法中与终结符有关的操作
        context.put("","");
        return null;
    }

}