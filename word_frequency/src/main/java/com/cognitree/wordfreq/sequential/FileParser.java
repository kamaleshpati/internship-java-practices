package com.cognitree.wordfreq.sequential;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class FileParser {

    private static final Logger log = LoggerFactory.getLogger(FileParser.class);

    HashMap<String, Integer> getWordFreq(String fileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        HashMap<String, Integer> uniqueWords = new HashMap<>();
        try (InputStream stream = loader.getResourceAsStream(fileName); BufferedReader bufferedReader = new
                BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)))) {
            bufferedReader.lines()
                    .map(String::toLowerCase)
                    .flatMap(row -> Arrays.stream(row.split("[\\s,;]")))
                    .map(word -> word.replaceAll("[().]", ""))
                    .forEachOrdered(wordToBePut -> {
                        int count = uniqueWords.getOrDefault(wordToBePut, 0);
                        uniqueWords.put(wordToBePut, count + 1);
                    });
        } catch (IOException ioexception) {
            log.error("getWordFreq : sources name incorrect = {}", fileName);
        }
        return uniqueWords;
    }
}
