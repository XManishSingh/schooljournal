package com.singh.journalApp.DTO;

import com.mongodb.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OTPValidationRequest {
    @NotBlank
    private String uuid;
    @NotBlank
    private String otp;
}
