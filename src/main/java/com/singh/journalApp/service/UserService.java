package com.singh.journalApp.service;

import com.singh.journalApp.entity.ClassDetails;
import com.singh.journalApp.entity.JournalEntry;
import com.singh.journalApp.entity.SubjectDetails;
import com.singh.journalApp.entity.User;
import com.singh.journalApp.repositry.ClassRepository;
import com.singh.journalApp.repositry.JournalEntryRepositry;
import com.singh.journalApp.repositry.SubjectRepository;
import com.singh.journalApp.repositry.UserRepositry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserService {
    @Autowired
    private UserRepositry userRepositry;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private EmailService emailService;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        user.setOtp(null);
        userRepositry.save(user);
    }
    public void saveNewUser(User user){
        userRepositry.save(user);
    }
    public List<User> getAll(){
        Sort sort = Sort.by(Sort.Direction.ASC, "userName");
        return userRepositry.findAll(sort);
    }
        public Optional<User> findById(ObjectId id){
        return userRepositry.findById(id);
    }
    public void deleteById(ObjectId id){
        userRepositry.deleteById(id);
    }
    public User findByUserName(String userName){
        return userRepositry.findByUserName(userName);
    }
    public void generateForgotPasswordToken(String email) {
        User user = userRepositry.findByEmail(email);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        user.setTokenExpirationTime(LocalDateTime.now().plusMinutes(30));

        userRepositry.save(user);

        emailService.sendResetMail(user.getEmail(), token);
    }
    public void resetPassword(String token, String newPassword) {
        User user = userRepositry.findByResetPasswordToken(token);
//                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (user.getTokenExpirationTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token has expired");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        user.setTokenExpirationTime(null);

        userRepositry.save(user);
        emailService.sendResetDone(user.getEmail());
    }
    public List<User> getUserPasswordNearToExpire(){
        LocalDateTime limit = LocalDateTime.now().plusDays(15);
        System.out.println(limit);
        return userRepositry.findUsersExpiredBefore(limit);
    }

    private static final SecureRandom random = new SecureRandom();
    private static final int OTP_LENGTH = 6;

    public static String generateOTP() {
        int otp = random.nextInt((int) Math.pow(10, OTP_LENGTH));
        return String.format("%06d", otp);
    }
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    @Scheduled(cron = "0 * * * * *"	)
    public void sendExpiryNotify(){
        LocalDateTime limit = LocalDateTime.now().plusDays(15);
        List<User> entry = userRepositry.findUsersExpiredBefore(limit);
        System.out.println(entry);
        for (User row : entry){
            emailService.sendExpireAlert(row.getEmail(), row.getUserName());
            System.out.println(row.getEmail());
        }

    }




}
