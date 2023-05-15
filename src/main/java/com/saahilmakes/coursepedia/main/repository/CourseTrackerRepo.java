package com.saahilmakes.coursepedia.main.repository;

import com.saahilmakes.coursepedia.main.model.CourseTrackerModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseTrackerRepo extends MongoRepository<CourseTrackerModel, String> {
}
