package com.example.OtpService;


import lombok.Data;

@Data
public class OtpValidationRequest {
    private String email;
    private String otp;
}
