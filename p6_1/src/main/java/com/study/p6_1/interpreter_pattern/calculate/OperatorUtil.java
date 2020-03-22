package com.study.p6_1.interpreter_pattern.calculate;

import java.util.Stack;

public class OperatorUtil {

    public static boolean isOperator(String symbol) {
        return (symbol.equals("+") || symbol.equals("-") || symbol.equals("*"));
    }

    public static Interpreter getInterpreter(Stack<IArithmeticInterpreter> numStack, Stack<String> operatorStack) {
        IArithmeticInterpreter rightExpr = numStack.pop();
        IArithmeticInterpreter leftExpr = numStack.pop();
        String symbol = operatorStack.pop();
        System.out.println("数字出栈："+rightExpr.interpret()+","+leftExpr.interpret()+",操作符出栈:"+symbol);
        if (symbol.equals("+")) {
            return new AddInterpreter(leftExpr, rightExpr);
        } else if (symbol.equals("-")) {
            return new SubInterpreter(leftExpr, rightExpr);
        } else if (symbol.equals("*")) {
            return new MultiInterpreter(leftExpr, rightExpr);
        } else if (symbol.equals("/")) {
            return new DivInterpreter(leftExpr, rightExpr);
        }
        return null;
    }
}