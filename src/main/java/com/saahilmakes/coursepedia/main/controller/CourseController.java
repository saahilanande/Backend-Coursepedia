package com.saahilmakes.coursepedia.main.controller;

import com.saahilmakes.coursepedia.main.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseRepo courseInterface;
}
