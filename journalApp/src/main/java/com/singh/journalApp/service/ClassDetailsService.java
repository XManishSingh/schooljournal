package com.singh.journalApp.service;

import com.singh.journalApp.entity.ClassDetails;
import com.singh.journalApp.repositry.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassDetailsService {
    @Autowired
    private ClassRepository classRepository;
    public void saveClas(ClassDetails cls){
        System.out.println(cls);
        classRepository.save(cls);
    }
    public List<ClassDetails> getClas(){
        return classRepository.findAll();
    }

}
