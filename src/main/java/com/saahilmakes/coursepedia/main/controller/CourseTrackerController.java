package com.saahilmakes.coursepedia.main.controller;

import com.saahilmakes.coursepedia.main.model.CourseTrackerModel;
import com.saahilmakes.coursepedia.main.repository.CourseTrackerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/tracker")
public class CourseTrackerController {

    @Autowired
    CourseTrackerRepo trackerInterface;

    //Get All tracked Courses
    @GetMapping("")
    public List<CourseTrackerModel> getAllTrackedCourse(){
       List<CourseTrackerModel> trackedCourses = trackerInterface.findAll();
       return trackedCourses;
    }

    //Add a course to track
    @PostMapping("/Addtracker/{course_id}/{user_id}")
    public String addTracker(@PathVariable("course_id") String course_id, @PathVariable("user_id") String user_id){
        try {
            CourseTrackerModel newTracker = new CourseTrackerModel();
            newTracker.setCourse_id(course_id);
            newTracker.setUser_id(user_id);
            trackerInterface.insert(newTracker);
            return "Course Added to Track Successfully";
        }
        catch (Exception ex){
            return ""+ex;
        }
    }

    //Delete a tracked course
    @DeleteMapping("/delete/{id}")
    public String deleteTrackerCourse(@PathVariable("id") String id){
        try{
            trackerInterface.deleteById(id);
            return "Item deleted with id"+id;
        }
        catch (Exception ex){
            return ""+ex;
        }
    }

}
