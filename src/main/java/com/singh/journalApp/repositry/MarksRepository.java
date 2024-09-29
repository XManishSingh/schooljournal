package com.singh.journalApp.repositry;

import com.singh.journalApp.entity.MarksDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MarksRepository extends MongoRepository<MarksDetails, String> {
    Optional<MarksDetails> findByExamCodeAndClassCodeAndMemberId(String examCode, String classCode, long memberId);
}
