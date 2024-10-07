package com.singh.journalApp.repositry;

import com.singh.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepositry extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
    User findByEmail(String email);
    User findByResetPasswordToken(String resetPasswordToken);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    void deleteByUserName(String userName);
    @Query("{ 'passwordExpireDate': { $lte: ?0 } }")
    List<User> findUsersExpiredBefore(LocalDateTime date);


}
