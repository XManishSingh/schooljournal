package com.singh.journalApp.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "student_details")
@Data
public class StudentDetails {
    @Id
    private long memberId;
    @NotBlank(message = "First Name is Required")
    private String firstName;
    @NotBlank(message = "Last Name is Required")
    private String lastName;
    @NotBlank(message = "Phone number is Required")
    @Size(message = "Phone No must be of 10 digits.")
    private String phone;
    private String sex;
    @NotNull(message = "DOB is Required")
    private Date dob;
    @NotNull(message = "Date of Enrollment is Required.")
    private Date enrollmentDate;
    private long rollNumber;
    @NotBlank(message = "Class is Required.")
    private String classCode;
    private Address address = new Address();
    private ParentDetails parentDetails = new ParentDetails();
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @NotBlank
    public static class Address{
        @NotBlank(message = "Street is Required")
        private String street;
        @NotBlank(message = "City is Required")
        private String city;
        @NotBlank(message = "State is Required")
        private String state;
        @Pattern(regexp = "\\d{5}", message = "Postal code must be 5 digits")
        private String postalCode;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParentDetails{
        private StudentDetails.ParentDetails.Parent father;
        private StudentDetails.ParentDetails.Parent mother;
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
    @DBRef
    private ClassDetails classDetails;
}
