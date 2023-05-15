package com.saahilmakes.coursepedia.main.repository;

import com.saahilmakes.coursepedia.main.model.CourseModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepo extends MongoRepository<CourseModel, String> {
}
