package com.cognitree.studentrecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class RecordParser {

    private static final Logger log = LoggerFactory.getLogger(RecordParser.class);

    private static Set<String> subjectSet;
    private static Set<String> classSet;

    RecordParser() {
        subjectSet = new HashSet<>();
        classSet = new HashSet<>();
    }

    boolean containsSubject(String subjectName) {
        return subjectSet.contains(subjectName);
    }

    boolean containsClass(String className) {
        return classSet.contains(className);
    }

    Map<Long, Student> parsingRecords(String fileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Map<Long, Student> studentData = new HashMap<>();
        try (InputStream stream = loader.getResourceAsStream(fileName); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] data = row.split(",");
                int noOfSubjects = data.length - 3;
                Student student = new Student(Long.parseLong(data[0]), data[1], data[2]);
                String[] subjects = new String[noOfSubjects];
                double[] marks = new double[noOfSubjects];
                for (int i = 3; i < data.length; i++) {
                    String[] subjectData = data[i].split("=");
                    subjects[i - 3] = subjectData[0];
                    subjectSet.add(subjectData[0]);
                    marks[i - 3] = Double.parseDouble(subjectData[1]);
                }
                classSet.add(data[2]);
                student.setMarks(subjects, marks);
                studentData.put(Long.parseLong(data[0]), student);
            }
        } catch (IOException ioexception) {
            log.error("setRecords : sources name incorrect = {}", fileName);
        }
        return studentData;
    }
}
