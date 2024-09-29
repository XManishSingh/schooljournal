package com.singh.journalApp.service;

import com.singh.journalApp.entity.GetExamReportRequest;
import com.singh.journalApp.entity.MarksDetails;
import com.singh.journalApp.entity.StudentDetails;
import com.singh.journalApp.entity.StudentExamReport;
import com.singh.journalApp.repositry.MarksRepository;
import com.singh.journalApp.repositry.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarksServices {
    @Autowired
    private MarksRepository marksRepository;
    @Autowired
    private StudentRepository studentRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    public ResponseEntity<?> saveStudentMarks(MarksDetails marksDetails){
        marksRepository.save(marksDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<StudentExamReport> getStudentExamReport(GetExamReportRequest getExamReportRequest){
        Optional<MarksDetails> report = marksRepository.findByExamCodeAndClassCodeAndMemberId(getExamReportRequest.getExamCode(), getExamReportRequest.getClassCode(), getExamReportRequest.getMemberId());
        if (report.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(report);
        StudentExamReport studentExamReport = modelMapper.map(report.get(), StudentExamReport.class);
        System.out.println(studentExamReport);
        Optional<StudentDetails> name = studentRepository.findStudentNameByMemberId(getExamReportRequest.getMemberId());
        if (name.isPresent()){
            StudentDetails nameDetails = name.get();
            String fullName = nameDetails.getFirstName()  + ' '+ nameDetails.getLastName();
            studentExamReport.setStudentName(fullName);
        }
        return new ResponseEntity<>(studentExamReport, HttpStatus.OK);
    }
}
