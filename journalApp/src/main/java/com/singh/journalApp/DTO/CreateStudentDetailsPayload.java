package com.singh.journalApp.DTO;

import com.singh.journalApp.entity.StudentDetails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class CreateStudentDetailsPayload {
    private long memberId;
    @NotBlank(message = "First Name is Required")
    private String firstName;
    @NotBlank(message = "Last Name is Required")
    private String lastName;
    @NotBlank(message = "Phone number is Required")
    @Size(message = "Phone No must be of 10 digits.")
    private String phone;
    private String sex;
    @NotBlank(message = "DOB is Required")
    private Date dob;
    @NotBlank(message = "Date of Enrollment is Required.")
    private Date enrollmentDate;
    private long rollNumber;
    @NotBlank(message = "Class is Required.")
    private String classCode;
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
}
