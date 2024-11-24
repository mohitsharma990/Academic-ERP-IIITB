package com.mohit.academicerp.security;

import com.mohit.academicerp.entities.Employee;
import com.mohit.academicerp.entities.User;
import com.mohit.academicerp.repositories.EmployeeRepository;
import com.mohit.academicerp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch User and Employee based on email
        User user = userRepository.findUserByEmail(email);
        Employee employee = employeeRepository.findByEmail(email);

        if (user == null || employee == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        // Map department name to GrantedAuthority
        GrantedAuthority authority = new SimpleGrantedAuthority(employee.getDepartment().getName());

        // Create UserDetails object
        return new org.springframework.security.core.userdetails.User(
                employee.getEmail(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }
}