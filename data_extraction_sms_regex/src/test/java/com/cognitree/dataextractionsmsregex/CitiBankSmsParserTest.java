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

class CitiBankSmsParserTest {

    private static final Logger log = LoggerFactory.getLogger(CitiBankSmsParserTest.class);

    @Test
    public void testGetData() {
        ArrayList<TransactionData> datas = new CitiBankSmsParser().getData("sampleciti.txt");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("testcitisample.txt");
        try (BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(Objects.requireNonNull(stream)))) {
            String line;
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\|");
                ArrayList<String> data = datas.get(count).getData();
                for (int i = 0; i < words.length; i++) {
                    if(!words[i].equals(" ")){
                        Assertions.assertEquals(words[i], data.get(i));
                    }
                }
                count++;
            }
        } catch (IOException ioexception) {
            log.error("getData : sources name incorrect");
        }

    }

}