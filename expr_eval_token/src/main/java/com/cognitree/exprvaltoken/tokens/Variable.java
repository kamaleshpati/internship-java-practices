package com.cognitree.exprvaltoken.tokens;

public class Variable extends Operand {

    public Variable(String expression) {
        super(expression);
    }

    public String getValue() {
        return super.expression;
    }
}
