package com.cognitree.studentrecord;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

public class RecordParserTest {

    private static final Logger log = LoggerFactory.getLogger(RecordParserTest.class);
    private final RecordParser recordParser = new RecordParser();
    private final ClassLoader loader = Thread.currentThread().getContextClassLoader();

    @Test
    public void testParsingRecordsWorking() {
        String file = "student_parser_working.txt";
        Map<Long, Student> studentData = recordParser.parsingRecords(file);
        try (InputStream stream = loader.getResourceAsStream(file); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                Assertions.assertTrue(studentData.containsKey(Long.parseLong(data[0])));
            }
        } catch (IOException ioexception) {
            log.error(ioexception.getMessage());
        }
    }

    @Test
    public void testParsingRecordsNotWorking() {
        String file = "student_parser_working.txt";
        Map<Long, Student> studentData = recordParser.parsingRecords(file);
        try (InputStream stream = loader.getResourceAsStream("student_parser_notworking.txt"); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                Assertions.assertFalse(studentData.containsKey(Long.parseLong(data[0])));
            }
        } catch (IOException ioexception) {
            log.error(ioexception.getMessage());
        }
    }
}