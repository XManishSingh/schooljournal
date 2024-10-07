package com.singh.journalApp.service;

import com.singh.journalApp.entity.User;
import com.singh.journalApp.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    //    @Autowired
    private final UserRepositry userRepositry;
    public UserDetailsServiceImpl(UserRepositry userRepositry) {
        this.userRepositry = userRepositry;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositry.findByUserName(username);
        if(user != null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: "+ username);

    }
}
