package com.singh.journalApp.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userIdSequence")
@Data
public class UserIdSequence {
    @Id
    private String id;
    private long sequence;
}
