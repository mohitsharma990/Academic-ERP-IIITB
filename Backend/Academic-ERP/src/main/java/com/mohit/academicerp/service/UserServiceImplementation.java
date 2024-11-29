package com.mohit.academicerp.service;



import com.mohit.academicerp.config.JwtProvider;
import com.mohit.academicerp.exception.JwtTokenNotValid;
import com.mohit.academicerp.model.User;
import com.mohit.academicerp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImplementation implements  UserService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email= JwtProvider.getEmailFromJwtToken(jwt);


        User user = userRepo.findByEmail(email);

        if(user==null) {
            throw new JwtTokenNotValid("Invalid credentials");
        }
        return user;
    }

    @Override
    public User findUserByEmail(String username) throws Exception {

        User user=userRepo.findByEmail(username);

        if(user!=null) {

            return user;
        }

        throw new JwtTokenNotValid("Invalid credentials");
    }

}