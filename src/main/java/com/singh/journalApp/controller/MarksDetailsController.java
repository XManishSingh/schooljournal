package com.singh.journalApp.controller;

import com.singh.journalApp.entity.GetExamReportRequest;
import com.singh.journalApp.entity.MarksDetails;
import com.singh.journalApp.entity.StudentExamReport;
import com.singh.journalApp.service.MarksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marks-details")
public class MarksDetailsController {
    @Autowired
    private MarksServices marksServices;
    @GetMapping("/student")
    public ResponseEntity<StudentExamReport> getStudentExamReport(@RequestBody GetExamReportRequest getExamReportRequest){
       return marksServices.getStudentExamReport(getExamReportRequest);

    }
    @PostMapping("/save-marks")
    public ResponseEntity<?> saveStudentMarks(@RequestBody MarksDetails marksDetails){
        marksServices.saveStudentMarks(marksDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

