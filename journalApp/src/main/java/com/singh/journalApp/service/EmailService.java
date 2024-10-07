package com.singh.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
//    public void sendResetMail(String mail, String token){
//        String resetLink = "https://yourapp.com/reset-password?token=" + token;
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(mail);
//        message.setSubject("Password Reset Request");
//        message.setText("Hi \nTo reset your password, click the link below:\n" + resetLink);
//        mailSender.send(message);
//    }
//    public void sendResetDone(String mail){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(mail);
//        message.setSubject("Password Reset Successfully");
//        message.setText("Hi \n Your Password has been reset Successfully");
//        mailSender.send(message);
//    }
//    public void sendExpireAlert(String mail, String userName){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(mail);
//        message.setSubject("Password ExpireAlert");
//        message.setText("Hi "+ userName + "\nYour Password is About to Expire please Reset it as soon as possible.");
//        mailSender.send(message);
//    }
//    public void sendOtpEmail(String toEmail, String otp, String userName){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(toEmail);
//        message.setSubject("OTP Verification");
//        message.setText("Hi "+ userName + "\nYour OTP for email verification is "+ otp +" please verify enter the otp and get verified in 5 minutes.");
//        mailSender.send(message);
//    }
    public void sendEmailGeneric(String toEmail, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
