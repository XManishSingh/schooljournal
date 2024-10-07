package com.singh.journalApp.repositry;

import com.singh.journalApp.entity.ClassDetails;
import com.singh.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassRepository extends MongoRepository<ClassDetails, String> {
    ClassDetails findByClassCode(String classCode);

}
