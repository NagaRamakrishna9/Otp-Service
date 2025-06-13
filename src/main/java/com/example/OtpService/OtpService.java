package com.example.OtpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final JavaMailSender mailSender;

    private final Map<String, OtpEntry> otpStorage = new ConcurrentHashMap<>();

    private final int otpLength = 6;
    private final Duration otpValidity = Duration.ofMinutes(5);

    @Autowired
    public OtpService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtp(String email) {
        String otp = generateOtp();
        otpStorage.put(email, new OtpEntry(otp, Instant.now().plus(otpValidity)));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
    }

    public boolean validateOtp(String email, String inputOtp) {
        OtpEntry entry = otpStorage.get(email);
        if (entry == null || Instant.now().isAfter(entry.expiryTime)) {
            return false;
        }
        return entry.otp.equals(inputOtp);
    }

    private String generateOtp() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private record OtpEntry(String otp, Instant expiryTime) {}
}
