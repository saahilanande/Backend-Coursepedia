package com.saahilmakes.coursepedia.main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/")
    public String getBaseUrl() {

        return "Coursepedia on Springboot";

    }
}
