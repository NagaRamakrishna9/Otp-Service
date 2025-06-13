package com.example.OtpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OtpController {
    @Autowired
    private OtpService otpService;

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestBody OtpRequest otpRequest) {
        otpService.sendOtp(otpRequest.getEmail());
        return ResponseEntity.ok("Otp sent successfully");
    }

    @PostMapping("/validation")
    public ResponseEntity<String> validateOtp(@RequestBody OtpValidationRequest otpValidationRequest) {
        boolean isValid = otpService.validateOtp(otpValidationRequest.getEmail(), otpValidationRequest.getOtp());
        return isValid ? ResponseEntity.ok("Valid OTP") : ResponseEntity.badRequest().body("Invalid or expired OTP");
    }
}
