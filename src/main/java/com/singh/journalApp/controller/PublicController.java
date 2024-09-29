package com.singh.journalApp.controller;

import com.singh.journalApp.DTO.OTPValidationRequest;
import com.singh.journalApp.DTO.UserRegistration;
import com.singh.journalApp.entity.ClassDetails;
import com.singh.journalApp.entity.SubjectDetails;
import com.singh.journalApp.entity.User;
import com.singh.journalApp.repositry.UserRepositry;
import com.singh.journalApp.service.EmailService;
import com.singh.journalApp.service.OTPService;
import com.singh.journalApp.service.UserService;
import com.singh.journalApp.utilis.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepositry userRepositry;
    @Autowired
    private EmailService emailService;
    @Autowired
    private OTPService otpService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/create-user")
    public boolean createUser(@RequestBody User user){
        user.setPasswordExpireDate(LocalDateTime.now().plusDays(4));
        userService.saveEntry(user);
        return true;
    }
    @PostMapping("/forgot-password")
    public boolean forgotPassword(@RequestParam("email") String email){
        userService.generateForgotPasswordToken(email);
        return true;
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String token, @RequestParam("password") String newPassword){
        try {
            userService.resetPassword(token, newPassword);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/password-expire")
    public ResponseEntity<?> passwordExpiryCheck(){
        try{
            userService.sendExpiryNotify();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/register-user")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user){
        if(!userRepositry.existsByEmail(user.getEmail()) && !userRepositry.existsByUserName(user.getUserName())){
            String otp = UserService.generateOTP();
            String uuid = UserService.generateUUID();
            otpService.storeUserWithOtp(uuid, otp, user);
            emailService.sendOtpEmail(user.getEmail(), otp, user.getUserName());
            return  new ResponseEntity<>(uuid, HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("Please check userName or Email.",HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OTPValidationRequest otpValidationRequest){
        if (otpService.validateOtp(otpValidationRequest.getUuid(), otpValidationRequest.getOtp())){
            return new ResponseEntity<>("User has got registered successfully.", HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Please enter valid otp.", HttpStatus.OK);
        }
    }
    @PostMapping("/check-cache")
        public void checkCache(){
            otpService.printAllCacheEntries();
        }


    }

