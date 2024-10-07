package com.singh.journalApp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class UserRegistration {
    @NotBlank(message = "User Name is required.")
    private String userName;
    @NotBlank(message = "Password is Required.")
    private String password;
    @Email
    @NotBlank(message = "Email is Required.")
    @Indexed(unique = true)
    private String email;
    private String otp;
}
