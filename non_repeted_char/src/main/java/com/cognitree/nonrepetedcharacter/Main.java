package com.cognitree.nonrepetedcharacter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.debug("main : enter the expression : ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input == null) {
            log.error("Main : null as input");
            System.exit(0);
        }
        Character output = new NonRepeatedCharacters().findUnique(input);
        if (output == null) {
            log.debug("main : no unique characters in {}", input);
        } else {
            log.debug("main : output={}", output);
        }
    }
}
