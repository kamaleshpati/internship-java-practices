package com.cognitree.studentrecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        String fileName = "students_record.txt";
        log.debug("main: filename {}", fileName);
        RecordQueryManager recordQueryManager = new RecordQueryManager(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice = "yes";
        while (choice.charAt(0) == 'Y' || choice.charAt(0) == 'y') {
            String className = null;
            while (className == null) {
                log.debug("main : enter the class name : ");
                className = br.readLine();
                if (!recordQueryManager.containsClass(className)) {
                    log.warn("main : {} not a valid class ", className);
                    className = null;
                }
            }
            String subjectName = null;
            while (subjectName == null) {
                log.debug("main : enter the subject name : ");
                subjectName = br.readLine();
                if (!recordQueryManager.containsSubject(subjectName)) {
                    log.warn("main : {} not a valid subject ", subjectName);
                    subjectName = null;
                }
            }
            int numberTobeSeen = 0;
            while (numberTobeSeen == 0) {
                try {
                    log.debug("main : enter the value of N:");
                    numberTobeSeen = Integer.parseInt(br.readLine());
                    log.debug("{}",numberTobeSeen);
                } catch (NumberFormatException e) {
                    log.error("main : not a number {}", numberTobeSeen);
                    numberTobeSeen = 0;
                }
            }
            List<Student> topNStudents = recordQueryManager.getRecords(className, subjectName,
                    numberTobeSeen);
            if (topNStudents != null) {
                for (Student student : topNStudents) {
                    log.debug("main :name = {}", student.getStudentName());
                }
            }
            log.debug("main : again want to do operation? [yes/no] : ");
            choice = br.readLine();
        }
    }
}
