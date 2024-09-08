package com.singh.journalApp.controller;

import com.singh.journalApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restServices/students")
public class StudentController {
    @Autowired
    private StudentService studentService;


}
