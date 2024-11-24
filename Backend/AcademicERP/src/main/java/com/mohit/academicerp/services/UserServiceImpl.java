package com.mohit.academicerp.services;

import com.mohit.academicerp.dto.UserDTO;
import com.mohit.academicerp.entities.Department;
import com.mohit.academicerp.entities.User;
import com.mohit.academicerp.repositories.DepartmentRepository;
import com.mohit.academicerp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           DepartmentRepository departmentRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO userDto) {
        // Check if the employee exists and assign a department if needed
        Department department = checkRoleExist();

        User user = new User();
        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encryptedPassword);
        user.setActive(userDto.getActive());
        user.setExpired(userDto.getExpired());
        user.setEmployee(userDto.getEmployee());

        // Ensure the employee has a department or assign one if missing
        if (user.getEmployee().getDepartment() == null) {
            user.getEmployee().setDepartment(department);
        }

        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email); // Corrected method name
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream()
                .map(this::mapToUserDto)  // More concise method reference
                .collect(Collectors.toList());
    }

    private UserDTO mapToUserDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setFirstName(user.getEmployee().getFirstName());
        userDto.setLastName(user.getEmployee().getLastName());
        userDto.setEmail(user.getEmployee().getEmail());
        return userDto;
    }

    private Department checkRoleExist() {
        // If no department exists with name "ADMIN", create and return one
        Department department = departmentRepository.findByName("ADMIN");
        if (department.equals(null)) {
//            department = Optional.Optionalof(new Department());
            department.setName("ADMIN");
            department.setCapacity(2);

            department = departmentRepository.save(department);
        }
        return department;
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = userRepository.findUserByEmail(username);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;  // Return false if no user found
    }
}