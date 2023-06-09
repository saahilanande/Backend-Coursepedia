package com.saahilmakes.coursepedia.main.controller;

import com.saahilmakes.coursepedia.main.model.CourseModel;
import com.saahilmakes.coursepedia.main.repository.CourseRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseRepo courseRepo;

    public CourseController(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    //Endpoint to add a new course
    @PostMapping("/addcourse")
    public CourseModel addUserById(@RequestBody @Valid CourseModel reqcourse) {

        CourseModel newCourse = new CourseModel();
        newCourse.setName(reqcourse.getName());
        newCourse.setDescription(reqcourse.getDescription());
        newCourse.setDifficulty(reqcourse.getDifficulty());
        newCourse.setLink(reqcourse.getLink());
        newCourse.setType(reqcourse.getType());
        newCourse.setCategory(reqcourse.getCategory());
        newCourse.setVideo(reqcourse.getVideo());

        courseRepo.insert(newCourse);

        return newCourse;
    }

    //Endpoint to get All courses
    @GetMapping("")
    public List<CourseModel> getAllproducts() {
        List<CourseModel> courses = courseRepo.findAll();
        return courses;
    }

    //Endpoint to Delete a course
    @GetMapping("/delete/{id}")
    public String deletecourse(@PathVariable("id") @NotEmpty String id) {
        try {
            courseRepo.deleteById(id);
            return "Course deleted Succesfully with ID" + id;
        } catch (Exception ex) {
            return "" + ex;
        }
    }
}
