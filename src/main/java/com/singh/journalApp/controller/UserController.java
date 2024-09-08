package com.singh.journalApp.controller;

import com.singh.journalApp.entity.User;
import com.singh.journalApp.repositry.UserRepositry;
import com.singh.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepositry userRepositry;
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB = userService.findByUserName(userName);
        System.out.println(userInDB);
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userService.saveEntry(userInDB);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepositry.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @GetMapping("/expire")
    public List<User> getPaawordExpiry(){
       return userService.getUserPasswordNearToExpire();
    }




}




