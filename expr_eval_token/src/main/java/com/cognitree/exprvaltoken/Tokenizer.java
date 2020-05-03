package com.cognitree.exprvaltoken;

import com.cognitree.exprvaltoken.tokens.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Tokenizer {

    private static final Logger log = LoggerFactory.getLogger(Tokenizer.class);

    private final String expression;

    Tokenizer(String expression) {
        this.expression = expression;
    }

    ArrayList<Token> getTokens() {
        ArrayList<Token> token = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            if (isOperator(expression.charAt(i))) {
                token.add(new Operator(expression.charAt(i)));
            } else if (expression.charAt(i) == '(' || expression.charAt(i) == ')') {
                token.add(new Braces(expression.charAt(i)));
            } else {
                if (Character.isAlphabetic(expression.charAt(i))) {
                    int start = i;
                    while (i < expression.length() && !isOperator(expression.charAt(i)))
                        i++;
                    token.add(new Variable(expression.substring(start, i)));
                } else {//it encounters the number
                    int start = i;
                    while (i < expression.length() && Character.isDigit(expression.charAt(i)))
                        i++;
                    token.add(new ConstNumber(expression.substring(start, i)));
                }
                i--;
            }
        }
        log.debug("getTokens: size of token = {}", token.size());
        return token;
    }

    private boolean isOperator(char character) {
        switch (character) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
                return true;
        }
        return false;
    }
}

