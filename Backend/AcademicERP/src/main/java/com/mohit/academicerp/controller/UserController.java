package com.mohit.academicerp.controller;

import com.mohit.academicerp.dto.UserDTO;
import com.mohit.academicerp.entities.User;
import com.mohit.academicerp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to create a new user
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        try {
            userService.saveUser(userDTO);  // Call to service layer
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user.");
        }
    }

    // Endpoint to get all users
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> users = userService.findAllUsers();  // Call to service layer
            return ResponseEntity.ok(users);  // Return list of users
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Endpoint to get a user by email
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        try {
            User user = userService.findUserByEmail(email);  // Call to service layer
            if (user != null) {
                UserDTO userDTO = new UserDTO();
                userDTO.setFirstName(user.getEmployee().getFirstName());
                userDTO.setLastName(user.getEmployee().getLastName());
                userDTO.setEmail(user.getEmployee().getEmail());
                return ResponseEntity.ok(userDTO);  // Return user details
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Endpoint to authenticate a user
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestParam String email, @RequestParam String password) {
        try {
            boolean isAuthenticated = userService.authenticate(email, password);  // Call to service layer
            if (isAuthenticated) {
                return ResponseEntity.ok("Authentication successful.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during authentication.");
        }
    }
}