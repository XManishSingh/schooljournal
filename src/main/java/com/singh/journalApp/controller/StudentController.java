package com.singh.journalApp.controller;

import com.singh.journalApp.entity.GetAllStudentsDetails;
import com.singh.journalApp.entity.StudentDetails;
import com.singh.journalApp.service.StudentService;
import com.singh.journalApp.service.UserIdSequenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("/restServices")
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserIdSequenceService userIdSequenceService;
    @GetMapping("/get-student-details")
    public ResponseEntity<?> getStudentDetails(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){
        try {
            GetAllStudentsDetails studentDetails = studentService.getAllStudentsDetails(page, size);
            return new ResponseEntity<>(studentDetails, HttpStatus.OK);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/create-student")
    public void createStudent(@Valid @RequestBody StudentDetails studentDetails){
        long roll = userIdSequenceService.getNextRoll(studentDetails.getClassCode());
        studentDetails.setRollNumber(roll);
        studentService.saveStudentDetails(studentDetails);
    }
}
