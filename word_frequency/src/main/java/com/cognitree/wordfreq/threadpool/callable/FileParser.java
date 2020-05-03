package com.cognitree.wordfreq.threadpool.callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileParser {

    private static final Logger log = LoggerFactory.getLogger(FileParser.class);

    private final HashMap<String, Integer> wordFreq;

    FileParser() {
        wordFreq = new HashMap<>();
    }

    public HashMap<String, Integer> getWordFreq(String fileName) {
        ArrayList<String> lines = getLines(fileName);
        int threadSize = getSize(lines.size());
        ExecutorService executor = Executors.newFixedThreadPool(threadSize);
        List<Future<HashMap<String, Integer>>> futures = IntStream.range(0, threadSize)
                .<Callable<HashMap<String, Integer>>>mapToObj(i ->
                        (i == (threadSize - 1)) ? (() ->
                                setWordFreq(lines.subList(i * threadSize, lines.size()))) : (() ->
                                setWordFreq(lines.subList(i * threadSize, (i + 1) * threadSize))))
                .map(executor::submit)
                .collect(Collectors.toList());
        try {
            joinMap(futures);
        } catch (ExecutionException | InterruptedException e) {
            log.debug("getWordFreq : {}", e.getMessage());
        }
        return wordFreq;
    }

    private void joinMap(List<Future<HashMap<String, Integer>>> futures)
            throws ExecutionException, InterruptedException {
        for (Future<HashMap<String, Integer>> hashMapFuture : futures) {
            hashMapFuture.get().forEach((key, value) -> {
                if (!wordFreq.containsKey(key)) {
                    wordFreq.put(key, value);
                } else {
                    wordFreq.put(key, wordFreq.get(key) + value);
                }
            });
        }
    }

    private HashMap<String, Integer> setWordFreq(List<String> list) {
        HashMap<String, Integer> wordMap = new HashMap<>();
        list.stream().flatMap(line -> Arrays.stream(line.toLowerCase()
                .split("[\\s,;]"))).map(word -> word.replaceAll("[().]", ""))
                .forEachOrdered(wordToBePut -> {
                    int count = wordMap.getOrDefault(wordToBePut, 0);
                    wordMap.put(wordToBePut, count + 1);
                });
        return wordMap;
    }

    private int getSize(int size) {
        if (size <= 10) {
            return 2;
        } else if (size <= 20) {
            return 4;
        } else if (size < 100) {
            if (size % 10 == 0) {
                return (size / 10);
            } else {
                return (size / 10) + 1;
            }
        } else
            return 10;
    }


    private ArrayList<String> getLines(String fileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        ArrayList<String> lines = new ArrayList<>();
        try (InputStream stream = loader.getResourceAsStream(fileName);
             BufferedReader bufferedReader = new BufferedReader
                     (new InputStreamReader(Objects.requireNonNull(stream)))) {
            lines = (ArrayList<String>) bufferedReader.lines()
                    .collect(Collectors
                            .toList());
        } catch (IOException ioexception) {
            log.error("getLines : sources name incorrect = {}", fileName);
        }
        return lines;
    }
}
