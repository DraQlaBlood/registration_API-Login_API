package com.mars.registrationservice.dao;


import com.mars.registrationservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDao extends MongoRepository<User, UUID> {
    User findByEmail(String email);

    User findByPseudo(String pseudo);

    @Query("{'verificationToken.token': ?0 }")
    User findByToken(String token);



}
