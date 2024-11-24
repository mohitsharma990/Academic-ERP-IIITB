package com.mohit.academicerp.services;

import com.mohit.academicerp.dto.UserDTO;
import com.mohit.academicerp.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(UserDTO userDto);

    User findUserByEmail(String email);

    List<UserDTO> findAllUsers();

    boolean authenticate(String username, String password);
}
