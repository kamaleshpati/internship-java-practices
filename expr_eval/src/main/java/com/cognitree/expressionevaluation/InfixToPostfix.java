package com.cognitree.expressionevaluation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class InfixToPostfix {

    private static final Logger log = LoggerFactory.getLogger(InfixToPostfix.class);

    private int getPrecedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // to postfix expression.
    StringBuffer infixToPostfix(StringBuffer expression) {
        StringBuffer result = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); ++i) {
            char character = expression.charAt(i);
            if (Character.isLetterOrDigit(character))
                result.append(character);
            else if (character == '(')
                stack.push(character);
            else if (character == '{')
                stack.push(character);
            else if (character == '[')
                stack.push(character);
            else if (character == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.append(stack.pop());
                if (!stack.isEmpty() && stack.peek() != '(') {
                    log.warn("infixToPostfix: Invalid Expression");
                    System.exit(0);
                } // invalid expression
                else
                    stack.pop();
            } else if (character == '}') {
                while (!stack.isEmpty() && stack.peek() != '{')
                    result.append(stack.pop());
                if (!stack.isEmpty() && stack.peek() != '{') {
                    log.error("infixToPostfix: Invalid Expression");
                    System.exit(0);
                } // invalid expression
                else
                    stack.pop();
            } else if (character == ']') {
                while (!stack.isEmpty() && stack.peek() != '[')
                    result.append(stack.pop());

                if (!stack.isEmpty() && stack.peek() != '[') {
                    log.error("infixToPostfix: Invalid Expression");
                    System.exit(0);
                } // invalid expression
                else
                    stack.pop();
            } else // an operator is encountered
            {
                while (!stack.isEmpty() && getPrecedence(character) <= getPrecedence(stack.peek())) {
                    if (stack.peek() == '(' || stack.peek() == '{' || stack.peek() == '[') {
                        log.error("infixToPostfix: Invalid Expression");
                        System.exit(0);
                    }
                    result.append(stack.pop());
                }
                stack.push(character);
            }
        }
        while (!stack.isEmpty()) { // pop all the operators from the stack
            if (stack.peek() == '(') {
                log.error("infixToPostfix: Invalid Expression");
                System.exit(0);
            }
            result.append(stack.pop());
        }
        log.debug("infixToPostfix: result = {}", result);
        return result;
    }
}
