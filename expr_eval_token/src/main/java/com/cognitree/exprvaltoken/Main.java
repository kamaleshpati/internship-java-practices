package com.cognitree.exprvaltoken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        log.debug("main: given expression = {}", expression);
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(expression);
        Set<String> variables = expressionEvaluator.getVariables();
        String choice = "yes";
        while (choice.charAt(0) == 'Y' || choice.charAt(0) == 'y') {
            HashMap<String, Number> values = new HashMap<>();
            log.info("main: variables {}", values);
            for (String var : variables) {
                System.out.println("Enter the value for " + var + ":");
                try {
                    String tempVar = scanner.next();
                    for (int i = 0; i < tempVar.length(); i++) {
                        if (!Character.isDigit(tempVar.charAt(i)) && tempVar.charAt(i) != '.') {
                            log.error("main: not a number = {}", tempVar);
                            System.exit(0);
                        }
                    }
                    log.trace("main: number = {}", tempVar);
                    values.put(var, Float.parseFloat(tempVar));
                } catch (NullPointerException e) {
                    log.warn("main: NullPointerException thrown!");
                }
            }
            System.out.println("result = " + expressionEvaluator.evaluate(values));
            System.out.println("re-evaluate? [yes/no]:");
            choice = scanner.next();
        }
    }
}

