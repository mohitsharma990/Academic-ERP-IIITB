package com.mohit.academicerp.controller;

import com.mohit.academicerp.entities.Employee;
import com.mohit.academicerp.services.EmployeeService;
import com.mohit.academicerp.utility.DepartmentUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentUtility departmentUtility;

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        try {
            Boolean employeeExists = employeeService.employeeExists(employee);
            if(Boolean.TRUE.equals(employeeExists)) {
                return ResponseEntity.status(500).body("Employee already exists.");
            }
            Employee result = employeeService.addEmployee(employee);
            return ResponseEntity.of(Optional.of(result));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("There is an Exception");
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllEmployee() {
        try {
            List<Employee> result = employeeService.getAllEmployee();
            return ResponseEntity.of(Optional.of(result));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("There is an Exception");
        }
    }

    @GetMapping(value = "/get-emp/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id) {
        try {
            Optional<Employee> result = employeeService.getEmployee(id);
            return ResponseEntity.of(Optional.of(result));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("There is an Exception");
        }
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getEmployeeByDepartment(@PathVariable int id) {
        try {
            Boolean departmentExist = departmentUtility.existDepartment(id);
            if(Boolean.FALSE.equals(departmentExist)) {
                return ResponseEntity.status(500).body("Department does not exist.");
            }
            List<Employee> result = employeeService.getEmployeeByDepartment(id);
            return ResponseEntity.of(Optional.of(result));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("There is an Exception.");
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.of(Optional.of("{ 'status' : 'success' }"));
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("There is an Exception.");
        }
    }
}