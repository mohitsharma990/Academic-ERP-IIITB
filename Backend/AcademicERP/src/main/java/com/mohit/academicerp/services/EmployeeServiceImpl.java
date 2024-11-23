package com.mohit.academicerp.services;

import com.mohit.academicerp.entities.Employee;
import com.mohit.academicerp.repositories.DepartmentRepository;
import com.mohit.academicerp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Boolean employeeExists(Employee employee) {
        List<Employee> employeeList = employeeRepository.findAll();
        for (Employee employee1 : employeeList) {
            if (employee1.getFirst_name().equals(employee.getFirst_name()) &&
                    employee1.getLast_name().equals(employee.getLast_name()) &&
                    employee1.getEmail().equals(employee.getEmail())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployee(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getEmployeeByDepartment(int departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
