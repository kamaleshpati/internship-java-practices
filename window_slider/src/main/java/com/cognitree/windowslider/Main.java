package com.cognitree.windowslider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static List<Number> generateRandom(int size) {
        ArrayList<Number> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int number = (int) (Math.random() * ((size) + 1));
            arrayList.add(number);
        }
        return arrayList;
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Number> list = Main.generateRandom(100);
        int batchSize = 0;
        while (batchSize == 0) {
            try {
                log.debug("main : enter the value of window size:");
                batchSize = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                log.error("main : not a number ");
                batchSize = 0;
            }
        }
        WindowIterator<Number> windowIterator = new WindowIterator<>(list, batchSize);
        while (windowIterator.hasNext()) {
            log.debug("main : {}", windowIterator.next());
        }
    }
}
