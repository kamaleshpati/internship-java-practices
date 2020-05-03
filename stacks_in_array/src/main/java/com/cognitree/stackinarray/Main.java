package com.cognitree.stackinarray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static Integer intValue(String input) {
        int in;
        try {
            in = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            log.error(exception.getMessage());
            return null;
        }
        return in;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        log.debug("main : enter the size : ");
        String input;
        Integer size = null;
        while (size == null) {
            input = scanner.next();
            size = intValue(input);
        }
        StackInArray<Integer> stack = new StackInArray<>(Integer.class, size);
        String choice = "yes";
        while (choice.charAt(0) == 'Y' || choice.charAt(0) == 'y') {
            log.debug("main : what kind of operation you want on Stack ?\n1.push\n2.pop");
            Integer op = null;
            while (op == null) {
                input = scanner.next();
                op = intValue(input);
            }
            if (op > 2 || op < 1) {
                log.error("main : not valid input");
                continue;
            }
            log.debug("main : on which Stack u want ur operation ?\n1.First\n2.Second");
            Integer stackNum = null;
            while (stackNum == null) {
                input = scanner.next();
                stackNum = intValue(input);
            }
            if (stackNum > 2 || stackNum < 1) {
                log.error("main : not valid input");
                continue;
            }
            if (op == 1) {
                log.debug("main : pushOperation : enter the obj :");
                Integer in = null;
                while (in == null) {
                    input = scanner.next();
                    in = intValue(input);
                }
                try {
                    stack.push(in, stackNum);
                } catch (OverFlowException e) {
                    log.error(e.toString());
                    continue;
                }
                try {
                    Integer peek = stack.peek(stackNum);
                    if (peek != null)
                        log.debug("main : {} element is pushed stack{};", peek, stackNum);
                } catch (UnderFlowException e) {
                    log.error(e.toString());
                    continue;
                }
            }
            if (op == 2) {
                try {
                    Integer output = stack.pop(stackNum);
                    if (output != null)
                        log.debug("main : {} element is poped from stack{};", output, stackNum);
                } catch (UnderFlowException e) {
                    log.error(e.toString());
                    continue;
                }
            }
            stack.display(stackNum);
            log.debug("main : again want to do operation? [yes/no] : ");
            choice = scanner.next();
        }
    }
}
