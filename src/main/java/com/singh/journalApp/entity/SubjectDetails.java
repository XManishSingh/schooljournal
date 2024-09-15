package com.singh.journalApp.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.bson.types.ObjectId;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subject_details")
@Data
public class SubjectDetails {
    @Id
    private ObjectId id;
    @NotBlank
    @NotBlank
    @Indexed(unique = true)
    private String subjectCode;
    @NotBlank
    private String subjectName;
    @NotBlank
    private int fullMarks;
    @NotBlank
    private String classCode;
}
