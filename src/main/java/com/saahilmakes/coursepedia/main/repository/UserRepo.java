package com.saahilmakes.coursepedia.main.repository;

import com.saahilmakes.coursepedia.main.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<UserModel, String> {

    @Query("{ 'id': ?0}")
    List<UserModel> findByQueryId(String id);

    @Query("{ 'email': ?0, 'password':?1}")
    UserModel validateUser(String email, String password);

    @Query("{'email':?0}")
    UserModel findUsername(String email);
}
