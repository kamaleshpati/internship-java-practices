package com.cognitree.batchiterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static ArrayList<Number> generateRandom(int size) {
        ArrayList<Number> randomNumbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int number = (int) (Math.random() * ((size) + 1));
            randomNumbers.add(number);
        }
        return randomNumbers;
    }

    public static void main(String[] args) {
        ArrayList<Number> data = Main.generateRandom(100);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int batchSize = 0;
        while (batchSize == 0) {
            log.debug("main : enter the value of batch size:");
            try {
                batchSize = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                log.error("main : {} ", e.getMessage());
                batchSize = 0;
            }
        }
        BatchIterator<Number> batchIterator = new BatchIterator<>(data, batchSize);
        while (batchIterator.hasNext())
            log.debug("main : {}", batchIterator.next());
    }
}
