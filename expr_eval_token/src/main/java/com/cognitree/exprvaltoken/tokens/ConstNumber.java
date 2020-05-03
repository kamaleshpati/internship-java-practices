package com.cognitree.exprvaltoken.tokens;

public class ConstNumber extends Operand {

    public ConstNumber(String expression) {
        super(expression);
    }

    public Number getValue() {
        return Float.parseFloat(super.expression);
    }
}
