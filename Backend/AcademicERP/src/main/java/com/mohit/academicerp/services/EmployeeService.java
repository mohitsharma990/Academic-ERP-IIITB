package com.mohit.academicerp.services;

import com.mohit.academicerp.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Boolean employeeExists(Employee employee);

    Employee addEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Optional<Employee> getEmployee(int id);

    List<Employee> getEmployeeByDepartment(int departmentId);

    void deleteEmployee(int id);
}
