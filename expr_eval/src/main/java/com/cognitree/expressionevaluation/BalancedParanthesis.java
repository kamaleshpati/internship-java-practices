package com.cognitree.expressionevaluation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class BalancedParanthesis {

    private static final Logger log = LoggerFactory.getLogger(BalancedParanthesis.class);

    boolean isMatchingPair(char character1, char character2) {
        if (character1 == '(' && character2 == ')')
            return true;
        else if (character1 == '{' && character2 == '}')
            return true;
        else if (character1 == '[' && character2 == ']')
            return true;
        else
            return false;
    }

    boolean areParenthesisBalanced(StringBuffer expression) {
        Stack<Character> stack = new Stack<>();
        // Traverse the given expression to check matching parenthesis
        for (int i = 0; i < expression.length(); i++) {
            //If the exp[i] is a starting parenthesis then push it
            if (expression.charAt(i) == '{' || expression.charAt(i) == '(' || expression.charAt(i) == '[')
                stack.push(expression.charAt(i));
            if (expression.charAt(i) == '}' || expression.charAt(i) == ')' || expression.charAt(i) == ']') {
                if (stack.isEmpty()) {
                    return false;
                } else if (!isMatchingPair(stack.pop(), expression.charAt(i))) {
                    return false;
                }
            }
        }
        log.debug("areParenthesisBalanced: result = {}", stack.isEmpty());
        return stack.isEmpty(); /*balanced*/
    }
}
