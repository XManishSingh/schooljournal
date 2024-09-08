package com.singh.journalApp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistration {
    @NotBlank(message = "User Name is required.")
    private String userName;
    @NotBlank(message = "Password is Required.")
    private String password;
    @Email
    @NotBlank(message = "Email is Required.")
    private String email;
    private String otp;
}
