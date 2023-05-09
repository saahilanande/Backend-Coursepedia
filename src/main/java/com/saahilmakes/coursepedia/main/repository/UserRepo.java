package com.saahilmakes.coursepedia.main.repository;

import com.saahilmakes.coursepedia.main.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserModel, Long> {
}
