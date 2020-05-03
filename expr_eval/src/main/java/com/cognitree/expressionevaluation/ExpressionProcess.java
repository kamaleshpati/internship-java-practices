package com.cognitree.expressionevaluation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpressionProcess {

    private static final Logger log = LoggerFactory.getLogger(ExpressionProcess.class);

    private StringBuffer createPostfixExpression(StringBuffer input) {
        log.debug("createPostfixExpression: input = {} ", input);
        InfixToPostfix postfix = new InfixToPostfix();
        StringBuffer postfixString = postfix.infixToPostfix(input);
        if (postfixString == null) {
            log.error("areParenthesisBalanced: postfix conversion failed");
            System.exit(0);
        }
        return postfixString;
    }

    StringBuffer process(StringBuffer input) {
        String in = input.toString();
        StringBuffer postFix = new StringBuffer();
        int operatorCounter = 0;
        int variableCounter = 0;
        StringBuffer output = new StringBuffer(in.trim());
        BalancedParanthesis balancedParanthesis = new BalancedParanthesis();
        if (balancedParanthesis.areParenthesisBalanced(output)) {
            postFix = new StringBuffer(createPostfixExpression(input));
            log.debug("process: postfix expression = {}", postFix);
        } else {
            log.error("process: parenthesis is not balanced");
            System.exit(0);
        }
        for (int i = 0; i < postFix.length(); i++) {
            if (postFix.charAt(i) == '+' ||
                    postFix.charAt(i) == '*' || postFix.charAt(i) == '-' ||
                    postFix.charAt(i) == '/' || postFix.charAt(i) == '^')
                operatorCounter++;
            else
                variableCounter++;
        }
        if (operatorCounter == variableCounter - 1) {
            log.debug("process: postfix = {}", postFix);
            return postFix;
        } else {
            log.error("process: operator is not balanced");
            return null;
        }
    }
}
