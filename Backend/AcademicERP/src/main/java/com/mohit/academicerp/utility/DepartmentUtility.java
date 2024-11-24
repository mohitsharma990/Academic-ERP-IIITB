package com.mohit.academicerp.utility;

import com.mohit.academicerp.entities.Department;
import com.mohit.academicerp.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentUtility {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentUtility(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public boolean existDepartment(int id) {
        return departmentRepository.existsById(id);
    }

    public List<Department> getAllDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }

    public Optional<Department> getDepartment(int id) {
        return departmentRepository.findById(id);
    }

    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name);  // Ensure this method exists in the repository
    }
}
