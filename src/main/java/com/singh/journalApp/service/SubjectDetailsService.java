package com.singh.journalApp.service;

import com.singh.journalApp.entity.ClassDetails;
import com.singh.journalApp.entity.SubjectDetails;
import com.singh.journalApp.repositry.ClassRepository;
import com.singh.journalApp.repositry.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class SubjectDetailsService {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SubjectRepository subjectRepository;
    @Transactional
    public void saveSubject(SubjectDetails sub){
        System.out.println(sub);
        subjectRepository.save(sub);
        ClassDetails classDetails = classRepository.findById(sub.getClassCode())
                .orElseThrow(() -> new RuntimeException("Class not found"));
        if (classDetails.getSubjects() == null) {
            classDetails.setSubjects(new ArrayList<>());
        }
        classDetails.getSubjects().add(sub);
        classRepository.save(classDetails);
    }
}
