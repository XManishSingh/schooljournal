package com.singh.journalApp.DTO;

import com.singh.journalApp.entity.MarksDetails;
import lombok.Data;

import java.util.List;
@Data
public class StudentExamReport {
    private String id;
    private String classCode;
    private String className;
    private long memberId;
    private String studentName;
    private String examCode;
    private String examName;
    @Data
    public class SubjectMarks {
        private String subjectCode;
        private String subjectName;
        private int fullMarks;
        private int marksObtained;
        private double percentage;
    }
    private List<MarksDetails.SubjectMarks> subjectMarksList;
    private int totalFullMarks;
    private int totalMarksObtained;
    private double overallPercentage;
}
