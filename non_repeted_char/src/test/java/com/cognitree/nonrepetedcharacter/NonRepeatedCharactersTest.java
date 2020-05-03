package com.cognitree.nonrepetedcharacter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NonRepeatedCharactersTest {

    private static final Logger log = LoggerFactory.getLogger(NonRepeatedCharactersTest.class);

    private static final NonRepeatedCharacters nonRepeatedCharacters = new NonRepeatedCharacters();
    private static final ClassLoader loader = Thread.currentThread().getContextClassLoader();

    @Test
    public void testFindUniqueWorking() {
        InputStream stream = loader.getResourceAsStream("findunique_working.txt");
        if (stream == null) {
            log.debug("file name incorrect");
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                Character output = nonRepeatedCharacters.findUnique(data[0]);
                Assertions.assertEquals(data[1].charAt(0), output);
            }
            stream.close();
        } catch (IOException ioexception) {
            log.warn(ioexception.getMessage());
        }
    }

    @Test
    public void testFindUniqueNotWorking() {
        InputStream stream = loader.getResourceAsStream("findunique_notworking.txt");
        if (stream == null) {
            log.debug("file name incorrect");
            System.exit(0);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                Character output = nonRepeatedCharacters.findUnique(data[0]);
                Assertions.assertNotEquals(data[1], output);
            }
            stream.close();
        } catch (IOException ioexception) {
            log.warn(ioexception.getMessage());
        }
    }
}