package com.mohit.academicerp.services;

import com.mohit.academicerp.dto.UserDTO;
import com.mohit.academicerp.entities.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDTO userDto);

    User findUserByEmail(String email);

    List<UserDTO> findAllUsers();
}
