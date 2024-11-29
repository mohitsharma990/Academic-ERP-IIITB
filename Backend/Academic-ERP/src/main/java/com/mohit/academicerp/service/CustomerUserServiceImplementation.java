package com.mohit.academicerp.service;


import com.mohit.academicerp.exception.JwtTokenNotValid;
import com.mohit.academicerp.model.User;
import com.mohit.academicerp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserServiceImplementation implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);

        if(user==null) {

            throw new JwtTokenNotValid("Invalid credentials");
        }

        List<GrantedAuthority> authorities=new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),user.getPassword(),authorities);
    }
}