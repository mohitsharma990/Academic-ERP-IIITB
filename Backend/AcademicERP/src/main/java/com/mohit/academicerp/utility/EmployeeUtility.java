package com.mohit.academicerp.utility;

import com.mohit.academicerp.entities.Employee;
import com.mohit.academicerp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeUtility {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeUtility(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(int id) {
        return employeeRepository.findById(id);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeeByDept(int id) {
        return employeeRepository.findByDepartmentId(id);  // Ensure this method exists in the repository
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);  // Ensure this method exists in the repository
    }

    public int getDepartmentCount(int id) {
        return employeeRepository.countByDepartmentId(id);  // Ensure this method exists in the repository
    }
}
