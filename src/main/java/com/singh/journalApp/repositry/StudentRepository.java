package com.singh.journalApp.repositry;

import com.singh.journalApp.entity.StudentDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<StudentDetails, Long> {

      Page<StudentDetails> findAll(Pageable pageable);

}
