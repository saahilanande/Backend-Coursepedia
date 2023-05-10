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
    @GetMapping("/validateUser/{email}/{password}")
    public String validateUser(@PathVariable("email") String email, @PathVariable("password") String password) {

        UserModel validUser = userInterface.validateUser(email, password);
        if (validUser == null) {
            return "User is invalid";
        } else {
            return "User is Valid";
        }
    }

    //Endpoint to update a particular user with Id
    @PutMapping("/updateuser/{userId}")
    public String updateUser(@PathVariable("userId") String userId, @RequestBody UserModel user) {
        try{
            UserModel updateuser = new UserModel();
            updateuser.setId(userId);
            updateuser.setName(user.getName());
            updateuser.setPhone(user.getPhone());
            updateuser.setAge(user.getAge());
            updateuser.setEmail(user.getEmail());
            updateuser.setPassword(user.getPassword());
            UserModel updatedUser = userInterface.save(updateuser);
            return "Updated Succesfully";
        }
        catch (Exception ex) {
            return "Error: "+ex;
        }
    }

    //Endpoint to Delete a particular user
    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        try {
            userInterface.deleteById(userId);
            return "Deleted a user with UserId:" + userId;
        } catch (Exception ex) {
            return "Error: "+ex;
        }


    }

}
