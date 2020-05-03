package com.cognitree.expressionevaluation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class EvaluationOfExpression {

    private static final Logger log = LoggerFactory.getLogger(EvaluationOfExpression.class);

    int evaluatePostfix(String expression) {
        Stack<Integer> stack = new Stack<>();
        log.debug("evaluatePostfix: expression = {}", expression);
        // Scan all characters one by one
        for (int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);
            if (Character.isDigit(character)) {
                String stringNumber = "";
                while (Character.isDigit(expression.charAt(i)) || expression.charAt(i) != '|') {
                    stringNumber = stringNumber + expression.charAt(i);
                    i++;
                }
                log.debug("evaluatePostfix: stack pushed {}", stringNumber);
                stack.push(Integer.parseInt(stringNumber));
            }
            else {
                int value1 = stack.pop();
                log.debug("evaluatePostfix: stack top = {}", value1);
                int value2 = stack.pop();
                log.debug("evaluatePostfix: stack top = {}", value2);
                switch (character) {
                    case '+':
                        stack.push(value2 + value1);
                        break;
                    case '-':
                        stack.push(value2 - value1);
                        break;
                    case '/':
                        stack.push(value2 / value1);
                        break;
                    case '*':
                        stack.push(value2 * value1);
                        break;
                }
                log.debug("evaluatePostfix: stack top = {}", stack.peek());
            }
        }
        return stack.pop();
    }
}
