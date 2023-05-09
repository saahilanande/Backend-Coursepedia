package com.saahilmakes.coursepedia.main.controller;


import com.saahilmakes.coursepedia.main.model.UserModel;
import com.saahilmakes.coursepedia.main.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userInterface;


    //Endpoint to get all the users
    @GetMapping("/")
    public List<UserModel> getAllUser() {
        List<UserModel> userList = userInterface.findAll();
        return userList;
    }

    //Endpoint to get a single user
    @GetMapping("/{userId}")
    public String getUserById(@PathVariable("userId") String userId) {
        return "GET USER BY" + userId;
    }

    //Endpoint to Add a user to collection
    @PostMapping("/adduser")
    public String addUserById() {
        return "User ADDED";
    }

    //Endpoint to Validate a user and assign token
    @PostMapping("/validateUser/{username}/{password}")
    public String validateUser(@PathVariable("username") String username, @PathVariable("password") String password) {
        return "User is valid";
    }

    //Endpoint to update a particular user
    @PutMapping("/updateuser/{userId}")
    public String updateUser(@PathVariable("userId") String userId) {
        return "User updated";
    }

    //Endpoint to Delete a particular user
    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        return "Delete user by" + userId;
    }

}
