package com.mohit.academicerp.controller;

import com.mohit.academicerp.exception.JwtTokenNotValid;
import com.mohit.academicerp.model.Employee;
import com.mohit.academicerp.model.User;
import com.mohit.academicerp.service.EmployeeService;
import com.mohit.academicerp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") @RestController
@RequestMapping("api/emp")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;


    @PostMapping("add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee,@RequestHeader("Authorization") String jwt) throws Exception {
        if(jwt==null){
            throw new JwtTokenNotValid("jwt required...");
        }
        User user=userService.findUserProfileByJwt(jwt);

        if(user==null || !user.getRole().equals("ROLE_ERP_ADMIN")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return employeeService.addEmployee(employee);
    }

    @GetMapping("getEmpById/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id,@RequestHeader("Authorization") String jwt) throws Exception {
        if(jwt==null){
            throw new JwtTokenNotValid("jwt required...");
        }
        User user=userService.findUserProfileByJwt(jwt);

        if(user==null || !user.getRole().equals("ROLE_ERP_ADMIN")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return employeeService.getEmployeeById(id);
    }
}
