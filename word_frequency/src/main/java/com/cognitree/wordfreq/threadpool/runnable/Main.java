package com.cognitree.wordfreq.threadpool.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String filename = "sample.txt";
        long startTime = System.currentTimeMillis();
        Map<String, Integer> wordFreq = new FileParser().getWordFreq(filename);
        long endTime = System.currentTimeMillis();
        log.debug("main : size of map ={},time taken ={}", wordFreq.size(), (endTime - startTime));
    }
}
