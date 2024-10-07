package com.singh.journalApp.controller;

import com.singh.journalApp.entity.SubjectDetails;
import com.singh.journalApp.service.SubjectDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
@Tag(name = "Subject API's")
public class SubjectController {
    @Autowired
    private SubjectDetailsService subjectDetailsService;
    @PostMapping("/create-subject")
    public void createSub(@RequestBody SubjectDetails sub){
        subjectDetailsService.saveSubject(sub);
    }
}
