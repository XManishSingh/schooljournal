package com.scm.backend.scm_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
   @Id
   private String id;
   private String name;
   private String email;
   private String phoneNumber;
   private String address;
   private String picture;
   @Lob
   private String description;
   private boolean favourite = false;
   private String websiteLink;
   private String linkedInLink;
   private String instagramLink;
   private String cloudinaryImagePublicId;

   @ManyToOne //by default fetch Type is Eager
    private User user;
}
