package com.cognitree.wordfreq.sequential;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String filename = "sample.txt";
        long startTime = System.currentTimeMillis();
        HashMap<String, Integer> uniqueWords = new FileParser().getWordFreq(filename);
        long endTime = System.currentTimeMillis();
        log.debug("main : size of map ={},time taken ={}", uniqueWords.size(), (endTime - startTime));
    }
}
