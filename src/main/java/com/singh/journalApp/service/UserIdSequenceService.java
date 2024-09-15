package com.singh.journalApp.service;

import com.singh.journalApp.entity.UserIdSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserIdSequenceService {
    @Autowired
    private MongoOperations mongoOperations;

    public long getNextSequenceId(String key) {
        Query query = new Query(Criteria.where("id").is(key));
        UserIdSequence counter = mongoOperations.findOne(query, UserIdSequence.class);

        if (counter == null) {
            // Step 3: If the document does not exist, create a new document with the initial value of 2000.
            UserIdSequence newCounter = new UserIdSequence();
            newCounter.setId(key);
            newCounter.setSequence(2001);
            mongoOperations.save(newCounter);
            return 2000;
        }
        Update update = new Update().inc("sequence", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true);
        counter = mongoOperations.findAndModify(query, update, options, UserIdSequence.class);
        return counter != null ? counter.getSequence() : 2000;
    }
    public long getNextRoll(String key){
        Query query = new Query(Criteria.where("id").is(key));
        UserIdSequence counter = mongoOperations.findOne(query, UserIdSequence.class);

        if (counter == null) {
            UserIdSequence newCounter = new UserIdSequence();
            newCounter.setId(key);
            newCounter.setSequence(1);
            mongoOperations.save(newCounter);
            return 1;
        }
        Update update = new Update().inc("sequence", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true);
        counter = mongoOperations.findAndModify(query, update, options, UserIdSequence.class);
        return counter != null ? counter.getSequence() : 1;
    }
}
