package com.study.p6_1.interpreter_pattern.calculate;

/**
 * Created by Tom.
 */
public class MultiInterpreter extends Interpreter {

    public MultiInterpreter(IArithmeticInterpreter left, IArithmeticInterpreter right){
        super(left,right);
    }

    public int interpret() {
        return this.left.interpret() * this.right.interpret();
    }

}
