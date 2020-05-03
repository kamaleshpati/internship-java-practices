package com.cognitree.exprvaltoken.tokens;

public class Braces implements Token {

    private final char braces;

    public Braces(char braces) {
        this.braces = braces;
    }

    @Override
    public int getPrecedence() {
        return 0;
    }

    public boolean isRight() {
        return braces == '(';
    }

    public boolean isLeft() {
        return braces == ')';
    }

    public Character display() {
        return braces;
    }
}
