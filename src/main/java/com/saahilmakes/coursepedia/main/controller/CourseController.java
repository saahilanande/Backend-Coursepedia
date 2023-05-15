package com.saahilmakes.coursepedia.main.controller;

import com.saahilmakes.coursepedia.main.model.CourseModel;
import com.saahilmakes.coursepedia.main.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseRepo courseInterface;

    //Endpoint to add a new course
    @PostMapping("/addcourse")
    public CourseModel addUserById(@RequestBody CourseModel reqcourse) {

        CourseModel newCourse = new CourseModel();
        newCourse.setName(reqcourse.getName());
        newCourse.setDescription(reqcourse.getDescription());
        newCourse.setDifficulty(reqcourse.getDifficulty());
        newCourse.setLink(reqcourse.getLink());
        newCourse.setType(reqcourse.getType());
        newCourse.setCategory(reqcourse.getCategory());
        newCourse.setVideo(reqcourse.getVideo());

        courseInterface.insert(newCourse);

        return newCourse;
    }
}
