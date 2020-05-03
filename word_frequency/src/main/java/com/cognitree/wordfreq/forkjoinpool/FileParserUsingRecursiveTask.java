package com.cognitree.wordfreq.forkjoinpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class FileParserUsingRecursiveTask {

    private static final Logger log = LoggerFactory.getLogger(FileParserUsingRecursiveTask.class);

    private final HashMap<String, Integer> wordFreq;

    FileParserUsingRecursiveTask() {
        wordFreq = new HashMap<>();
    }

    public HashMap<String, Integer> getWordFreq(String fileName) {
        ArrayList<String> lines = getLines(fileName);
        int size = lines.size() / getSize(lines.size());
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        WordMap wordMap = new WordMap(lines, size);
        forkJoinPool.invoke(wordMap);
        return wordFreq;
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

    private class WordMap extends RecursiveTask<HashMap<String, Integer>> {

        private final List<String> lines;
        private final int size;

        public WordMap(List<String> lines, int size) {
            this.lines = lines;
            this.size = size;
        }

        @Override
        protected HashMap<String, Integer> compute() {
            if (lines.size() <= size) {
                return setWordFreq();
            } else {
                WordMap wordMap1 = new WordMap(lines.subList(0, size), size);
                WordMap wordMap2 = new WordMap(lines.subList(size, lines.size()), size);
                wordMap1.fork();
                wordMap2.fork();
                joinMap(wordMap1.join());
                joinMap(wordMap2.join());
                return wordFreq;
            }
        }

        private synchronized void joinMap(HashMap<String, Integer> wordMap) {
            wordMap.forEach((key, value) ->
                    wordFreq.put(key, !wordFreq.containsKey(key) ?
                            value : Integer.valueOf(wordFreq.get(key) + value)));
        }

        private HashMap<String, Integer> setWordFreq() {
            HashMap<String, Integer> wordMap = new HashMap<>();
            lines.stream()
                    .flatMap(line -> Arrays.stream(line.toLowerCase()
                            .split("[\\s,;]")))
                    .map(word -> word.replaceAll("[().]", ""))
                    .forEachOrdered(wordToBePut -> {
                        int count = wordMap.getOrDefault(wordToBePut, 0);
                        wordMap.put(wordToBePut, count + 1);
                    });
            return wordMap;
        }
    }
}
