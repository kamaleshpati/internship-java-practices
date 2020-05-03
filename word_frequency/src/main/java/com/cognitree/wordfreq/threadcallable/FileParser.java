package com.cognitree.wordfreq.threadcallable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
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
        ArrayList<Future<HashMap<String, Integer>>> futures = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        int threadSize = getSize(lines.size());
        IntStream.range(0, threadSize).forEachOrdered(i -> {
            Callable<HashMap<String, Integer>> callable = i == threadSize - 1 ? (() ->
                    setWordFreq(lines.subList(i * threadSize, lines.size()))) : (() ->
                    setWordFreq(lines.subList(i * threadSize, (i + 1) * threadSize)));
            FutureTask<HashMap<String, Integer>> futureTask = new FutureTask<>(callable);
            futures.add(futureTask);
            threads.add(new Thread(futureTask));
            threads.get(i).start();
        });
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.error("getWordFreq : {}", e.getMessage());
            }
        });
        joinMap(futures);
        return wordFreq;
    }

    private void joinMap(ArrayList<Future<HashMap<String, Integer>>> futures) {
        futures.forEach(task -> {
            try {
                task.get().forEach((key, value) -> wordFreq.put(key, !wordFreq.containsKey(key) ?
                        value : Integer.valueOf(wordFreq.get(key) + value)));
            } catch (InterruptedException | ExecutionException e) {
                log.error("joinMap : {}", e.getMessage());
            }
        });
    }

    private HashMap<String, Integer> setWordFreq(List<String> list) {
        HashMap<String, Integer> wordMap = new HashMap<>();
        list.stream()
                .flatMap(line -> Arrays.stream(line.toLowerCase()
                        .split("[\\s,;]")))
                .map(word -> word.replaceAll("[().]", ""))
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
