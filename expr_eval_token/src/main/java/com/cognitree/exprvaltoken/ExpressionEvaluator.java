package com.cognitree.exprvaltoken;

import com.cognitree.exprvaltoken.tokens.ConstNumber;
import com.cognitree.exprvaltoken.tokens.Operator;
import com.cognitree.exprvaltoken.tokens.Token;
import com.cognitree.exprvaltoken.tokens.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ExpressionEvaluator {

    private static final Logger log = LoggerFactory.getLogger(ExpressionEvaluator.class);

    private final ExpressionParser expressionParser;
    private final ArrayList<Token> postfix;

    ExpressionEvaluator(String expression) {
        expressionParser = new ExpressionParser(expression);
        postfix = expressionParser.getPostfix();
    }

    Set<String> getVariables() {
        Set<String> variables = new HashSet<>();
        for (Token tok : expressionParser.getUniqueVariables(postfix)) {
            variables.add((String) tok.display());
        }
        log.debug("getVariables: variables = {}", variables);
        return variables;
    }

    public Number evaluate(HashMap<String, Number> values) {
        Stack<Number> stack = new Stack<>();
        for (Token tok : postfix) {
            if (tok instanceof Variable)
                stack.push(values.get(((Variable) tok).getValue()));
            else if (tok instanceof ConstNumber)
                stack.push(((ConstNumber) tok).getValue());
            else {
                Number value1 = stack.pop();
                Number value2 = stack.pop();
                switch (((Operator) tok).value()) {
                    case '+':
                        stack.push(value2.floatValue() + value1.floatValue());
                        break;
                    case '-':
                        stack.push(value2.floatValue() - value1.floatValue());
                        break;
                    case '/':
                        stack.push(value2.floatValue() / value1.floatValue());
                        break;
                    case '*':
                        stack.push(value2.floatValue() * value1.floatValue());
                        break;
                }
            }
        }
        log.debug("evaluate: evaluated result = {}", stack.peek());
        return stack.pop();
    }
}
