package com.singh.journalApp.repositry;

import com.singh.journalApp.entity.StudentDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<StudentDetails, ObjectId> {

}
