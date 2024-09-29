package com.singh.journalApp.entity;

import lombok.Data;

@Data
public class GetExamReportRequest {
    private String examCode;
    private String classCode;
    private Long memberId;
}
