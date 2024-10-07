package com.singh.journalApp.service;

import com.singh.journalApp.DTO.UserRegistration;
import com.singh.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.cache.Cache;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OTPService {
    @Autowired
    private UserIdSequenceService userIdSequenceService;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private UserService userService;
    @CachePut(value = "otpCache", key = "#uuid")
    public UserRegistration storeUserWithOtp(String uuid, UserRegistration user){
        return user;
    }
    @Cacheable(value = "otpCache", key = "#uuid")
    public User getCachedUserWithOTP(String uuid) {
        return null;
    }
    @CacheEvict(value = "otpCache", key = "#uuid")
    public void removeCachedUserWithOTP(String uuid) {
    }
    @Transactional
    public boolean validateOtp(String uuid, String providedOtp) {
        printAllCacheEntries();
        Cache cache = cacheManager.getCache("otpCache");
        if (cache != null) {
            User cachedUser = cache.get(uuid, User.class);
            if (cachedUser != null) {
                System.out.println("User found in cache: " + cachedUser);
                if (providedOtp.equals(cachedUser.getOtp())) {
                    System.out.println("OTP validated successfully for UUID: " + uuid);
                    long memberId = userIdSequenceService.getNextSequenceId("userIdSequence");
                    System.out.println(memberId);
                    cachedUser.setMemberId(memberId);
                    userService.saveEntry(cachedUser);
                    removeCachedUserWithOTP(uuid);
                    return true;
                } else {
                    System.out.println("Invalid OTP provided.");
                }
            } else {
                System.out.println("No user found in cache for UUID: " + uuid);
            }
        } else {
            System.out.println("Cache not found.");
        }
        return false;
    }

    public void printAllCacheEntries() {
        Cache cache = cacheManager.getCache("otpCache");
        if (cache != null && cache.getNativeCache() instanceof com.github.benmanes.caffeine.cache.Cache) {
            com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache =
                    (com.github.benmanes.caffeine.cache.Cache<Object, Object>) cache.getNativeCache();

            nativeCache.asMap().forEach((key, value) -> {
                System.out.println("Key: " + key + ", Value: " + value);
            });
        } else {
            System.out.println("Cache not found or not a Caffeine cache.");
        }
    }
}
