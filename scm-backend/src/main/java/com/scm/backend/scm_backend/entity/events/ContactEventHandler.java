package com.scm.backend.scm_backend.entity.events;

import com.scm.backend.scm_backend.entity.Contact;
import com.scm.backend.scm_backend.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RepositoryEventHandler(Contact.class)
public class ContactEventHandler {
    private Logger log = LoggerFactory.getLogger(UserEventHandler.class);

    @HandleBeforeCreate
    public void handleBeforeCreate(Contact contact){
        log.info("going to create user");
        contact.setId(UUID.randomUUID().toString());
    }
}
