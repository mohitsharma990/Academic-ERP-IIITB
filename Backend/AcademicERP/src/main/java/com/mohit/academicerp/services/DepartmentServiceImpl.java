package com.mohit.academicerp.services;

import com.mohit.academicerp.entities.Department;
import com.mohit.academicerp.repositories.DepartmentRepository;
import com.mohit.academicerp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Boolean departmentExists(Department department) {
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department1 : departmentList) {
            if (department1.getName().equals(department.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartment(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Boolean existsDepartment(int id) {
        return departmentRepository.existsById(id);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }
}
