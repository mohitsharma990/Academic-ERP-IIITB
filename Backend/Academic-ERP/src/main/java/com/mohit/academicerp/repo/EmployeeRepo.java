package com.mohit.academicerp.repo;

import com.mohit.academicerp.model.Department;
import com.mohit.academicerp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByDepartment(Department department);


    void deleteByDepartment(Department department);
}
