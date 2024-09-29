package com.singh.journalApp.repositry;

import com.singh.journalApp.entity.StudentDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<StudentDetails, Long> {

      Page<StudentDetails> findAll(Pageable pageable);
      Page<StudentDetails> findByClassCode(String classCode, Pageable pageable);
      @Query(value = "{ 'memberId': ?0 }", fields = "{ 'firstName': 1, 'lastName': 1 }")
      Optional<StudentDetails> findStudentNameByMemberId(long memberId);

}
