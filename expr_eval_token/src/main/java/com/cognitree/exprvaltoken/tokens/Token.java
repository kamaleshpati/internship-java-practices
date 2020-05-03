package com.cognitree.exprvaltoken.tokens;

public interface Token<T> {

    int getPrecedence();

    T display();
}
