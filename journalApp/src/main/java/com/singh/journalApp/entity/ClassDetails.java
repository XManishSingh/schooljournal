package com.singh.journalApp.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "class_details")
@Data
public class ClassDetails {
    @Id
    private String classCode;
    private String className;
    private int totalStudents;
    @DBRef(lazy = false)
    private List<SubjectDetails> subjects = new ArrayList<>();

}
