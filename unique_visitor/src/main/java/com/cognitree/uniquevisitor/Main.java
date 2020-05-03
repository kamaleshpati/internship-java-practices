package com.cognitree.uniquevisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws ParseException {
        String fileName = "visitor.txt";
        log.debug("main: filename {}", fileName);
        VisitorAggregator visitorAggregator = new VisitorAggregator();
        int time = 0;
        char timeUnit = args[0].charAt(args[0].length() - 1);
        String timeValue = args[0].substring(0, args[0].length() - 1);
        if (timeUnit != 'h' && timeUnit != 'm') {
            log.error("main:  wrong time unit = {}", timeUnit);
            System.exit(0);
        }
        for (int i = 0; i < timeValue.length(); i++)
            if (!Character.isDigit(timeValue.charAt(i))) {
                log.error("main: wrong time value = {}", timeValue);
                System.exit(0);
            }
        if (timeUnit == 'm')
            time = Integer.parseInt(timeValue);
        if (timeUnit == 'h')
            time = Integer.parseInt(timeValue);
        visitorAggregator.countUnique(fileName, time, timeUnit);
    }
}
