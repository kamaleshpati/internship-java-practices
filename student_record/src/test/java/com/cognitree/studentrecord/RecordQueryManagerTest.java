package com.cognitree.studentrecord;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RecordQueryManagerTest {

    @Test
    public void testGetRecordsWorking() {
        String file = "student_parser_working.txt";
        RecordQueryManager recordQueryManager = new RecordQueryManager(file);
        List<Student> studentData = recordQueryManager.getRecords("xii", "math", 10);
        double mark = studentData.get(0).getMarks("math");
        Assertions.assertEquals(29, mark);
        mark = studentData.get(1).getMarks("math");
        Assertions.assertEquals(28, mark);
        mark = studentData.get(2).getMarks("math");
        Assertions.assertEquals(27, mark);
    }

    @Test
    public void testGetRecordsNotworking() {
        String file = "student_parser_working.txt";
        RecordQueryManager recordQueryManager = new RecordQueryManager(file);
        List<Student> studentData = recordQueryManager.getRecords("xii", "math", 10);
        double mark = studentData.get(0).getMarks("math");
        Assertions.assertNotEquals(30, mark, 0.0);
        mark = studentData.get(1).getMarks("math");
        Assertions.assertNotEquals(31, mark, 0.0);
        mark = studentData.get(2).getMarks("math");
        Assertions.assertNotEquals(32, mark, 0.0);
    }
}