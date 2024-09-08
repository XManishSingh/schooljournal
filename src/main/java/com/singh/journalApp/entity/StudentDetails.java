package com.singh.journalApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "student_details")
@Data
public class StudentDetails {
    @Id
    private String studentId;
    private String firstName;
    private String phone;
    private Date enrollmentDate;
    private int rollNumber;
    private String classCode;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address{
        private String street;
        private String city;
        private String state;
        private String postalCode;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParentDetails{
        private Parent father;
        private Parent mother;
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Parent{
            private String firstName;
            private String lastName;
            private String email;
            private String phone;
        }
    }
}
