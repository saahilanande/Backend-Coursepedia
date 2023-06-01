package com.saahilmakes.coursepedia.main.repository;

import com.saahilmakes.coursepedia.main.model.CourseTrackerModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTrackerRepo extends MongoRepository<CourseTrackerModel, String> {
}
