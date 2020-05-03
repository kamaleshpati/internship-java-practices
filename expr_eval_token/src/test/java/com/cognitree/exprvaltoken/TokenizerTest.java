package com.cognitree.exprvaltoken;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class TokenizerTest {

    private ClassLoader loader = Thread.currentThread().getContextClassLoader();
    private static final Logger log = LoggerFactory.getLogger(TokenizerTest.class);

    @Test
    public void testGetTokensWorking() {
        InputStream stream = loader.getResourceAsStream("testgettoken_working.txt");
        if (stream == null) {
            log.error("file name incorrect");
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                Tokenizer tokenizer = new Tokenizer(data[0]);
                Assertions.assertEquals(Integer.parseInt(data[1]), tokenizer.getTokens().size());
            }
            stream.close();
        } catch (IOException ioexception) {
            log.warn(ioexception.getMessage());
        }
    }

    @Test
    public void testGetTokensNotWorking() {
        InputStream stream = loader.getResourceAsStream("testgettoken_notworking.txt");
        if (stream == null) {
            log.error("file name incorrect");
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                Tokenizer tokenizer = new Tokenizer(data[0]);
                Assertions.assertNotEquals(Integer.parseInt(data[1]), tokenizer.getTokens().size());
            }
            stream.close();
        } catch (IOException ioexception) {
            log.warn(ioexception.getMessage());
        }
    }
}