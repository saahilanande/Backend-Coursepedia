package com.saahilmakes.coursepedia.main.service;

import com.saahilmakes.coursepedia.main.model.UserModel;
import com.saahilmakes.coursepedia.main.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserModel userFound = userRepo.findUsername(s);
        if(userFound == null){
            return null;
        }
        return new User(userFound.getName(),userFound.getPassword(),null);
    }
}
