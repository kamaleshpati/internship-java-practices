package com.cognitree.studentrecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecordQueryManager {

    private static final RecordParser recordParser = new RecordParser();

    private final Map<Long, Student> studentData;

    RecordQueryManager(String fileName) {
        studentData = recordParser.parsingRecords(fileName);
    }

    boolean containsClass(String className) {
        return recordParser.containsClass(className);
    }

    boolean containsSubject(String subjectName) {
        return recordParser.containsSubject(subjectName);
    }

    private ArrayList<Student> getRecords(String className) {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        for (long studentID : studentData.keySet()) {
            Student student = studentData.get(studentID);
            if (student.getClassName().equals(className))
                studentArrayList.add(student);
        }
        return studentArrayList;
    }

    private ArrayList<Student> getRecords(String className, String subjectName) {
        ArrayList<Student> studentList = getRecords(className);
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getMarks(subjectName) == -1)
                studentList.remove(i);
        }
        return studentList;
    }

    List<Student> getRecords(String className, String subjectName, int numberTobeSeen) {
        ArrayList<Student> studentList = getRecords(className, subjectName);
        studentList.sort((student1, student2) -> {
            if (student1.getMarks(subjectName) == student2.getMarks(subjectName))
                return 0;
            return student1.getMarks(subjectName) < student2.getMarks(subjectName) ? 1 : -1;
        });
        return studentList.subList(0, numberTobeSeen);
    }
}
