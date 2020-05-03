package com.cognitree.wordfreq.forkjoinpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String filename = "sample.txt";
        //time calculation for recursive task
        long startTime = System.currentTimeMillis();
        Map<String, Integer> wordFreqRecursiveTask = new FileParserUsingRecursiveTask().getWordFreq(filename);
        long endTime = System.currentTimeMillis();
        log.debug("main : size of map ={},time taken for recursive task ={}",
                wordFreqRecursiveTask.size(), (endTime - startTime));
        //time calculation for recursive action
        startTime = System.currentTimeMillis();
        Map<String, Integer> wordFreqRecursiveAction = new FileParserUsingRecursiveAction().getWordFreq(filename);
        endTime = System.currentTimeMillis();
        log.debug("main : size of map ={},time taken for recursive action ={}",
                wordFreqRecursiveAction.size(), (endTime - startTime));
    }
}
