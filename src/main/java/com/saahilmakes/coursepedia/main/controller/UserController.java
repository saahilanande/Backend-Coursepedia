package com.saahilmakes.coursepedia.main.controller;


import com.saahilmakes.coursepedia.main.model.UserModel;
import com.saahilmakes.coursepedia.main.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userInterface;


    //Endpoint to get all the users
    @GetMapping("")
    public List<UserModel> getAllUser(@RequestParam(value = "id", required = false) String userId) {

        if (userId == null) {
            List<UserModel> userList = userInterface.findAll();
            return userList;
        } else {
            List<UserModel> userList = userInterface.findByQueryId(userId);
            return userList;
        }
    }

    //Endpoint to Add a user to collection
    @PostMapping("/adduser")
    public UserModel addUserById(@RequestBody UserModel newuserModel) {

        UserModel newUser = new UserModel();
        newUser.setName(newuserModel.getName());
        newUser.setEmail(newuserModel.getEmail());
        newUser.setAge(newuserModel.getAge());
        newUser.setPassword(newuserModel.getPassword());
        newUser.setPhone(newuserModel.getPhone());

        userInterface.insert(newUser);

        return newUser;
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
