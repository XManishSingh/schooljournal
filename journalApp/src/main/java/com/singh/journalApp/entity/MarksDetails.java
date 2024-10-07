package com.singh.journalApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "marks-details")
public class MarksDetails {
    @Id
    private String id;
    private long memberId;
    private String classCode;
    private String examCode;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubjectMarks {
        private String subjectCode;
        private String subjectName;
        private int fullMarks;
        private int marksObtained;
        private double percentage;
    }
    private List<SubjectMarks> subjectMarksList;
    private int totalFullMarks;
    private int totalMarksObtained;
    private double overallPercentage;

}
