package com.mohit.academicerp.controller;

import com.mohit.academicerp.exception.JwtTokenNotValid;
import com.mohit.academicerp.model.Department;
import com.mohit.academicerp.model.User;
import com.mohit.academicerp.service.DepartmentService;
import com.mohit.academicerp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") @RestController
@RequestMapping("/api/department")
public class DeptController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public ResponseEntity<?> addDepartment(@RequestBody Department department, @RequestHeader("Authorization") String jwt) throws Exception {
        if(jwt==null){
            throw new JwtTokenNotValid("jwt required...");
        }
        User user=userService.findUserProfileByJwt(jwt);

        if(user==null || !user.getRole().equals("ROLE_ERP_ADMIN")){
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

        return departmentService.addDepartment(department);

    }
    @GetMapping("getAll")
    public ResponseEntity<?> getAllDepartments(@RequestHeader("Authorization") String jwt) throws Exception {
        if(jwt==null){
            throw new JwtTokenNotValid("jwt required...");
        }
        User user=userService.findUserProfileByJwt(jwt);

        if(user==null || !user.getRole().equals("ROLE_ERP_ADMIN")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return departmentService.getAllDepartments();
    }

    @PutMapping("update")
    public ResponseEntity<?> updateDepartment(@RequestBody Department department,@RequestHeader("Authorization") String jwt) throws Exception {
        if(jwt==null){
            throw new JwtTokenNotValid("jwt required...");
        }
        User user=userService.findUserProfileByJwt(jwt);

        if(user==null || !user.getRole().equals("ROLE_ERP_ADMIN")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id,@RequestHeader("Authorization") String jwt) throws Exception {
        if(jwt==null){
            throw new JwtTokenNotValid("jwt required...");
        }
        User user=userService.findUserProfileByJwt(jwt);

        if(user==null || !user.getRole().equals("ROLE_ERP_ADMIN")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return departmentService.deleteDepartment(id);
    }
}











