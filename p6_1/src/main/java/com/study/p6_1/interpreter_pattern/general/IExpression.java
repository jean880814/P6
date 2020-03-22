package com.study.p6_1.interpreter_pattern.general;

// 抽象表达式
public interface IExpression {
    // 对表达式进行解释
    Object interpret(Context context);
}