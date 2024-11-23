package com.mohit.academicerp.services;

import com.mohit.academicerp.entities.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Boolean departmentExists(Department department);

    Department addDepartment(Department department);

    List<Department> getAllDepartments();

    Optional<Department> getDepartment(int id);

    Boolean existsDepartment(int id);

    void deleteDepartment(int id);
}
