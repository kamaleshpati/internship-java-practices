package com.cognitree.exprvaltoken.tokens;

public abstract class Operand implements Token {

    final String expression;

    Operand(String expression) {
        this.expression = expression;
    }

    @Override
    public int getPrecedence() {
        return 0;
    }

    @Override
    public String display() {
        return expression;
    }

}
