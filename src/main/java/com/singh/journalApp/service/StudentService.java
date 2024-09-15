package com.singh.journalApp.service;

import com.singh.journalApp.entity.*;
import com.singh.journalApp.repositry.ClassRepository;
import com.singh.journalApp.repositry.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClassRepository classRepository;
    private void getStudentDetails(String userId) {
    }
    public ResponseEntity<?> saveStudentDetails(StudentDetails studentDetails){
        ClassDetails classDetails = classRepository.findByClassCode(studentDetails.getClassCode());
        studentDetails.setClassDetails(classDetails);
        studentRepository.save(studentDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public GetAllStudentsDetails getAllStudentsDetails(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentDetails> studentPage = studentRepository.findAll(pageable);

        List<GetStudentResponse> studentResponseList = studentPage.stream()
                .map(student -> modelMapper.map(student, GetStudentResponse.class))
                .toList();
        GetAllStudentsDetails allStudentsDetails = new GetAllStudentsDetails();
        allStudentsDetails.setStudentDetailsList(studentResponseList);
        allStudentsDetails.setTotalPages(studentPage.getTotalPages());
        allStudentsDetails.setCurrentPage(studentPage.getNumber());
        allStudentsDetails.setTotalElements(studentPage.getTotalElements());

        return allStudentsDetails;
    }

}
