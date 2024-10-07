package com.singh.journalApp.controller;

import com.singh.journalApp.entity.ClassDetails;
import com.singh.journalApp.service.ClassDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassDetailsService classDetailsService;
    @PostMapping("/create-class")
    public void createClass(@RequestBody ClassDetails cls){
        System.out.println(cls);
        classDetailsService.saveClas(cls);
    }
    @GetMapping("/class")
    public List<ClassDetails> getClas(){
        return classDetailsService.getClas();
    }
}
