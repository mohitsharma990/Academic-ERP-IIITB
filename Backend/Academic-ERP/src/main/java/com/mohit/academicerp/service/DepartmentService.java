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

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    public ResponseEntity<?> addDepartment(Department department) {
        departmentRepo.save(department);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllDepartments() {
        return new ResponseEntity<>(departmentRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> updateDepartment(Department department) {
        departmentRepo.save(department);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteDepartment(int id) {
        Department department = departmentRepo.findById(id).get();
        List<Employee>emp= employeeRepo.findAllByDepartment(department);
        for(Employee employee : emp){
            employeeRepo.delete(employee);
        }
        departmentRepo.deleteById(id);
        return new ResponseEntity<>(department,HttpStatus.OK);
    }
}
