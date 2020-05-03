package com.cognitree.expressionevaluation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class InfixToPostfixTest {

    private final InfixToPostfix infixToPostfix = new InfixToPostfix();
    private ClassLoader loader = Thread.currentThread().getContextClassLoader();
    private static final Logger log = LoggerFactory.getLogger(InfixToPostfixTest.class);

    @Test
    public void testInfixToPostfixWorking() {
        InputStream stream = loader.getResourceAsStream("in_postfix_working.txt");
        if (stream == null) {
            log.error("file name incorrect");
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                StringBuffer infix = new StringBuffer(data[0]);
                Assertions.assertEquals(infixToPostfix.infixToPostfix(infix).toString(), data[1]);
            }
            stream.close();
        } catch (IOException ioexception) {
            log.warn(ioexception.getMessage());
        }
    }

    @Test
    public void testInfixToPostfixNotWorking() {
        InputStream stream = loader.getResourceAsStream("in_postfix_not_working.txt");
        if (stream == null) {
            log.error("file name incorrect");
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                StringBuffer infix = new StringBuffer(data[0]);
                Assertions.assertNotEquals(infixToPostfix.infixToPostfix(infix).toString(), data[1]);
            }
            stream.close();
        } catch (IOException ioexception) {
            log.warn(ioexception.getMessage());
        }
    }
}