package com.saahilmakes.coursepedia.main.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/")
    public String getAllUser() {
        return "GET ALL USER";
    }

    @GetMapping("/{userId}")
    public String getUserById(@PathVariable("userId") String userId) {
        return "GET USER BY" + userId;
    }

    @PostMapping("/adduser")
    public String addUserById(){
        return "User ADDED";
    }

    @PostMapping("/validateUser/{username}/{password}")
    public String validateUser(@PathVariable("username") String username, @PathVariable("password") String password){
        return "User is valid";
    }

    @PutMapping("/updateuser/{userId}")
    public  String updateUser(@PathVariable("userId") String userId){
        return "User updated";
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        return "Delete user by" + userId;
    }

}
