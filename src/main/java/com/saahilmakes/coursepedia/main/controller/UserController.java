package com.saahilmakes.coursepedia.main.controller;


import com.saahilmakes.coursepedia.main.model.UserModel;
import com.saahilmakes.coursepedia.main.repository.UserRepo;
import com.saahilmakes.coursepedia.main.service.TokenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userInterface;

    @Autowired
    AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public UserController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    //Endpoint to get all the users
    @GetMapping("/")
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
    public ResponseEntity<Object> validateUser(@PathVariable("email") @NotBlank @Email String email, @PathVariable("password") @NotBlank String password) {

        Map<String, String> data;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            String token = tokenService.generateToken(email);// Signing and generating a JWT token
            data = new HashMap<>();
            data.put("Email", email);
            data.put("Jwt", token);
        } catch (BadCredentialsException ex) {

            return new ResponseEntity<>("Invalid User", HttpStatus.FORBIDDEN);

        }
        return new ResponseEntity<>(data, HttpStatus.OK);
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
