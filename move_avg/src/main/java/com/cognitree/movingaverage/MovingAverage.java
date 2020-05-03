package com.cognitree.movingaverage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *the code will take the list of  integer and pass it to the circular list
 * it also calculate average using the circular list iterator
 */
public class MovingAverage {

    private static final Logger log = LoggerFactory.getLogger(MovingAverage.class);

    static Number findAverage(CircularList<Integer> circularList, int window) {
        if (circularList == null) {
            log.error("findAverage: circular list is empty");
            System.exit(0);
        }
        int sumOfList = 0;
        for (Integer num : circularList) {
            sumOfList = sumOfList + num;
        }
        log.debug("findAverage: average = {}", sumOfList / window);
        return sumOfList / window;
    }

    public static void main(String[] args) throws IOException {
        int windowSize = Integer.parseInt(args[0]);
        Integer[] list = new Integer[windowSize];
        log.debug("main: window size {}", windowSize);
        CircularList<Integer> circularList = new CircularList<>(list); // passing  list the to circular list
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int counter = 0;
        while (counter < 100) {
            // read the line and convert it into integer
            String input = br.readLine();
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    log.error("main: input format error = {}", input);
                    System.exit(0);
                }
            }
            circularList.add(Integer.parseInt(input));   //passing input to the circularList
            if (counter < windowSize - 1)
                log.debug("main: too less object ,objects required {}", windowSize - counter - 1); // print statement if the input is not enough
            else
                System.out.println(findAverage(circularList, windowSize));                   // call the average function
            counter++;
        }
    }
}
