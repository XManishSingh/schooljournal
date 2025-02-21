package com.scm.backend.scm_backend.repository;

import com.scm.backend.scm_backend.entity.Contact;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "user-contact") // now instead of contact we will use this path
public interface ContactRepository extends JpaRepository<Contact, String> {

    // /contacts/search/email = abc@gmail.com --> by default path to run api from repo
    Contact findByEmail(String email);


    Contact findByPhoneNumber(String phoneNumber);

}
