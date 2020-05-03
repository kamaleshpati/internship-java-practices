package com.cognitree.expressionevaluation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/*
 * code is to take the expression and then
 * create postfix
 * take the input according to the variable
 * and then find result
 */
public class ExpressionEvaluationMain {

    private static final Logger log = LoggerFactory.getLogger(ExpressionEvaluationMain.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuffer input = new StringBuffer(scanner.nextLine());
        log.debug("main: input = {}", input);
        ExpressionProcess process = new ExpressionProcess();
        while (true) {
            String expressionNum = "";
            StringBuffer postfix = process.process(input);
            if (postfix == null) {
                log.error("main: postfix is null");
                System.exit(0);
            }
            for (int i = 0; i < postfix.length(); i++) {
                //here it will take input from user
                // it add an additional expression '|'
                if (postfix.charAt(i) >= 'a' && postfix.charAt(i) <= 'z' ||
                        postfix.charAt(i) >= 'A' && postfix.charAt(i) <= 'Z') {
                    System.out.println("enter the value for variable " + postfix.charAt(i));
                    String value = scanner.next();
                    for (int j = 0; j < value.length(); j++) {
                        if (!Character.isDigit(value.charAt(j))) {
                            log.error("main: format error of input {}", value);
                            System.exit(0);
                        }
                    }
                    expressionNum = expressionNum + value + '|';
                } else
                    expressionNum = expressionNum + postfix.charAt(i);
            }
            EvaluationOfExpression evaluation = new EvaluationOfExpression();
            int result = evaluation.evaluatePostfix(expressionNum);
            System.out.println("result is " + result);
            log.debug("main: result = {}", result);
        }
    }
}
