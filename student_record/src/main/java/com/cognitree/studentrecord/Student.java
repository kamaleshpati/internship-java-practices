package com.cognitree.studentrecord;

import java.util.HashMap;
import java.util.Map;

class Student {

    private Long studentId;
    private String studentName;
    private String className;
    private Map<String, Double> subjectData;

    public Student(Long studentId, String studentName, String className) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        subjectData = new HashMap<>();
    }

    String getStudentName() {
        return this.studentName;
    }

    String getClassName() {
        return this.className;
    }

    double getMarks(String subject) {
        if (!subjectData.containsKey(subject))
            return -1;
        else
            return subjectData.get(subject);
    }

    void setMarks(String[] subject, double[] marks) {
        for (int i = 0; i < subject.length; i++) {
            subjectData.put(subject[i], marks[i]);
        }
    }
}
