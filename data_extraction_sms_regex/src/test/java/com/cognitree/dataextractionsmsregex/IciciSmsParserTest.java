package com.cognitree.dataextractionsmsregex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.IntStream;

public class IciciSmsParserTest {

    private static final Logger log = LoggerFactory.getLogger(IciciSmsParserTest.class);

    @Test
    public void testGetData() {
        ArrayList<ArrayList<String>> data = new IciciSmsParser().getData("sampleicici.txt");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("testsample.txt");
        try (BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(Objects.requireNonNull(stream)))) {
            String line;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\|");
                int finalCount = count;
                IntStream.range(0, words.length)
                        .forEachOrdered(i -> Assertions.assertEquals(words[i], data.get(finalCount).get(i)));
                count++;
            }
        } catch (IOException ioexception) {
            log.error("getData : sources name incorrect");
        }
    }
}