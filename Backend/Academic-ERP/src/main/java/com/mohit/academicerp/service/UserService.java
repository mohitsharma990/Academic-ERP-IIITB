package com.mohit.academicerp.service;


import com.mohit.academicerp.model.User;

import java.util.List;

public interface UserService {

    public User findUserProfileByJwt(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;


}