package com.mohit.academicerp.utility;

import com.mohit.academicerp.entities.User;
import com.mohit.academicerp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserUtility {

    private final UserRepository userRepository;

    @Autowired
    public UserUtility(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    public User validateUser(String email, String password) {
        return userRepository.validateUser(email, password);  // Ensure that validateUser is correctly implemented
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);  // Corrected method name from finUserByEmail
    }
}