package com.singh.journalApp.DTO;

import com.singh.journalApp.entity.ClassDetails;
import lombok.Data;

import java.util.Date;
@Data
public class GetStudentResponse {
        private long memberId;
        private String firstName;
        private String lastName;
        private String phone;
        private String sex;
        private Date dob;
        private Date enrollmentDate;
        private long rollNumber;
        private String classCode;
        private Address address;
        private ParentDetails parentDetails;
        private ClassDetails classDetails;
        @Data
        public static class Address {
            private String street;
            private String city;
            private String state;
            private String postalCode;

        }
        @Data
        public static class ParentDetails {
            private Parent father;
            private Parent mother;
            @Data
            public static class Parent {
                private String firstName;
                private String lastName;
                private String email;
                private String phone;
            }
        }

}
