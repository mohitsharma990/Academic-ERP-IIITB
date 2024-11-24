package com.mohit.academicerp.controller;

import com.mohit.academicerp.dto.LoginDTO;
import com.mohit.academicerp.dto.RegistrationDTO;
import com.mohit.academicerp.dto.UserDTO;
import com.mohit.academicerp.entities.Department;
import com.mohit.academicerp.entities.Employee;
import com.mohit.academicerp.entities.User;
import com.mohit.academicerp.services.UserServiceImpl;
import com.mohit.academicerp.utility.DepartmentUtility;
import com.mohit.academicerp.utility.EmployeeUtility;
import com.mohit.academicerp.utility.UserUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/handle")
public class LoginRegistrationController {

    @Autowired
    public DepartmentUtility departmentUtility;
    @Autowired
    public EmployeeUtility employeeUtility;
    @Autowired
    public UserUtility userUtility;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> RegisterUser(@RequestBody RegistrationDTO registrationDTO) {
        try {
            boolean validDepartment = validateDepartment(registrationDTO.getDepartment());
            if (!validDepartment) return ResponseEntity.status(500).body("Invalid department provided.");

            boolean validEmployee = validateEmployee(registrationDTO.getEmail());
            if (!validEmployee) return ResponseEntity.status(500).body("Invalid employee details provided.");

            // Get Department
            Department department = departmentUtility.getDepartment(Integer.parseInt(registrationDTO.getDepartment()));
            int count = employeeUtility.getDepartmentCount(Integer.parseInt(registrationDTO.getDepartment()));
            if (department == null || department.getCapacity() <= count) {
                return ResponseEntity.status(500).body("Department strength has reached maximum.");
            }

            // Add Employee
            Employee employee = new Employee();
            employee.setEmail(registrationDTO.getEmail());
            employee.setFirstName(registrationDTO.getFirstName());
            employee.setLastName(registrationDTO.getLastName());
            employee.setTitle(registrationDTO.getTitle());
            employee.setPhotoPath(registrationDTO.getPhotoPath().toString());
            employee.setDepartment(department);
            employee = employeeUtility.addEmployee(employee);
            if (employee == null || employee.getId() == 0) {
                return ResponseEntity.status(500).body("Employee could not be added.");
            }

            // Add User
            UserDTO userDto = new UserDTO();
            userDto.setEmployee(employee);
            userDto.setActive(true);
            userDto.setExpired(false);
            userDto.setPassword(registrationDTO.getPassword());
            userService.saveUser(userDto);

            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("There is an Exception.");
        }
    }

    private boolean validateDepartment(String id) {
        try {
            int did = Integer.parseInt(id);
            return departmentUtility.existDepartment(did);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validateEmployee(String email) {
        try {
            Employee employee = employeeUtility.getEmployeeByEmail(email);
            return employee == null || employee.getId() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        boolean valid = userService.authenticate(username, password);

        if (valid) {
            User user = userUtility.findUserByEmail(loginDTO.getEmail());
            if (user.getActive()) {
                Employee employee = employeeUtility.getEmployeeByEmail(loginDTO.getEmail());
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Your profile is not active. Contact Admin.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }

    @GetMapping(value = "/getSession")
    public ResponseEntity<String> getSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No session found.");
        }
        return ResponseEntity.ok(session.getAttribute("user").toString());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Logout successful");
    }
}