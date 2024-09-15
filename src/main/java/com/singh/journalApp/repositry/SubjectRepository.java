package com.singh.journalApp.repositry;

import com.singh.journalApp.entity.ClassDetails;
import com.singh.journalApp.entity.SubjectDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<SubjectDetails, ObjectId> {

}
