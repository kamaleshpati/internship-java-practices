package com.cognitree.wordfreq.threadrunnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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
        ArrayList<Thread> threads = new ArrayList<>();
        int threadSize = getSize(lines.size());
        IntStream.range(0, threadSize).forEachOrdered(i -> {
            Runnable runnable = (i == (threadSize - 1)) ? () ->
                    setWordFreq(lines.subList(i * threadSize, lines.size())) : () ->
                    setWordFreq(lines.subList(i * threadSize, (i + 1) * threadSize));
            threads.add(new Thread(runnable));
            threads.get(i).start();
        });
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.error("getWordFreq : {}", e.getMessage());
            }
        });
        return wordFreq;
    }

    private void setWordFreq(List<String> list) {
        for (String line : list) {
            for (String word : line.toLowerCase().split("[\\s,;]")) {
                String wordToBePut = word.replaceAll("[().]", "");
                synchronized (this) {
                    int count = wordFreq.getOrDefault(wordToBePut, 0);
                    wordFreq.put(wordToBePut, count + 1);
                }
            }
        }
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
