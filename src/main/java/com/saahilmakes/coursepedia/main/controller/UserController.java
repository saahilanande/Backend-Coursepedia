package com.saahilmakes.coursepedia.main.controller;


import com.saahilmakes.coursepedia.main.model.UserModel;
import com.saahilmakes.coursepedia.main.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userInterface;

    @Autowired
    AuthenticationManager authenticationManager;


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
    public UserModel addUserById(@RequestBody @Valid UserModel newuserModel) {

        try {
            UserModel newUser = new UserModel();
            newUser.setName(newuserModel.getName());
            newUser.setEmail(newuserModel.getEmail());
            newUser.setAge(newuserModel.getAge());
            newUser.setPassword(newuserModel.getPassword());
            newUser.setPhone(newuserModel.getPhone());

            userInterface.insert(newUser);

            return newUser;
        } catch (Exception ex) {
            UserModel emptyUser = new UserModel();
            return emptyUser;
        }
    }

    //Endpoint to Validate a user and assign token
    @GetMapping("/validateUser/{email}/{password}")
    public String validateUser(@PathVariable("email") @NotBlank @Email String email, @PathVariable("password") @NotBlank String password) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException ex) {

            return "Invalid User";

        }
        return "User is Valid";
    }

    //Endpoint to update a particular user with Id
    @PutMapping("/updateuser/{userId}")
    public String updateUser(@PathVariable("userId") @NotBlank String userId, @RequestBody @Valid UserModel user) {
        try {
            UserModel updateuser = new UserModel();
            updateuser.setId(userId);
            updateuser.setName(user.getName());
            updateuser.setPhone(user.getPhone());
            updateuser.setAge(user.getAge());
            updateuser.setEmail(user.getEmail());
            updateuser.setPassword(user.getPassword());
            userInterface.save(updateuser);
            return "Updated Succesfully";
        } catch (Exception ex) {
            return "Error: " + ex;
        }
    }

    //Endpoint to Delete a particular user
    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") @NotBlank String userId) {
        try {
            userInterface.deleteById(userId);
            return "Deleted a user with UserId:" + userId;
        } catch (Exception ex) {
            return "Error: " + ex;
        }


    }

}
