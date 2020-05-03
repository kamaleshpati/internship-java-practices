package com.cognitree.exprvaltoken.tokens;

public class Operator implements Token<Character> {

    private final char character;

    public Operator(char character) {
        this.character = character;
    }

    public int getPrecedence() {
        switch (character) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return 0;
    }

    @Override
    public Character display() {
        return character;
    }

    public Character value() {
        return character;
    }
}
