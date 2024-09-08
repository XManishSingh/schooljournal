package com.singh.journalApp.repositry;

import com.singh.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepositry extends MongoRepository<JournalEntry, ObjectId> {

}
