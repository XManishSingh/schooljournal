package com.singh.journalApp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

public class MarksDetails {
    @Id
    private String id;
    @DBRef
    private StudentDetails studentDetails;
    @DBRef
    private ClassDetails classDetails;
    @DBRef
    private SubjectDetails subjectDetails;
    private String examCode;
    private int marksObtained;
    private int fullMarks;
    private LocalDateTime examDate;


}
