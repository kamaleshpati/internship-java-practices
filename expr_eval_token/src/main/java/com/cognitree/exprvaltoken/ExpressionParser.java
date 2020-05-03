package com.cognitree.exprvaltoken;

import com.cognitree.exprvaltoken.tokens.Braces;
import com.cognitree.exprvaltoken.tokens.Operand;
import com.cognitree.exprvaltoken.tokens.Token;
import com.cognitree.exprvaltoken.tokens.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ExpressionParser {

    private static final Logger log = LoggerFactory.getLogger(ExpressionEvaluator.class);

    private final String expression;

    ExpressionParser(String expression) {
        this.expression = expression;
    }

    ArrayList<Token> getPostfix() {
        Tokenizer tokenizer = new Tokenizer(expression);
        ArrayList<Token> infixTokens = tokenizer.getTokens();
        ArrayList<Token> postfixToken = new ArrayList<>();
        Stack<Token> stack = new Stack<>();
        for (Token tok : infixTokens) {
            if (tok instanceof Operand) {
                postfixToken.add(tok);
            } else if (tok instanceof Braces && ((Braces) tok).isRight()) {
                stack.push(tok);
                System.out.println(((Braces) tok).display());
            } else if (tok instanceof Braces && ((Braces) tok).isLeft()) {
                while (!stack.isEmpty() && !((Braces) tok).isRight())
                    postfixToken.add(stack.pop());
                if (!stack.isEmpty() && !((Braces) tok).isRight()) {
                    log.error("getPostfix: expression invalid");
                    System.exit(0); // invalid expression
                } else
                    stack.pop();
            } else { // an operator is encountered
                while (!stack.isEmpty() && tok.getPrecedence() <= stack.peek().getPrecedence()) {
                    if (stack.peek() instanceof Braces) {
                        log.error("getPostfix: imbalanced expression");
                        System.exit(0);
                    }
                    postfixToken.add(stack.pop());
                }
                stack.push(tok);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek() instanceof Braces && ((Braces) stack.peek()).isRight()) {
                log.error("getPostfix: Invalid Expression");
                System.exit(0);
            }
            postfixToken.add(stack.pop());
        }
        return postfixToken;
    }

    Set<Token> getUniqueVariables(ArrayList<Token> postfix) {
        HashSet<Token> variables = new HashSet<>();
        for (Token tok : postfix) {
            if (tok instanceof Variable)
                variables.add(tok);
        }
        return variables;
    }
}
