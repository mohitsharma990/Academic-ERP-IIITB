package com.mohit.academicerp.service;

import com.mohit.academicerp.model.Department;
import com.mohit.academicerp.model.Employee;
import com.mohit.academicerp.repo.DepartmentRepo;
import com.mohit.academicerp.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    public ResponseEntity<?> addEmployee(Employee employee) {
        employeeRepo.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    public ResponseEntity<?> getEmployeeById(int id) {
        Department department = departmentRepo.findById(id).get();
        List<Employee> emp = employeeRepo.findAllByDepartment(department);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
}
