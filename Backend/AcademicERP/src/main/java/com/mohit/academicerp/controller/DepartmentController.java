package com.mohit.academicerp.controller;

import com.mohit.academicerp.dto.DetailsDTO;
import com.mohit.academicerp.entities.Department;
import com.mohit.academicerp.entities.Employee;
import com.mohit.academicerp.entities.User;
import com.mohit.academicerp.services.DepartmentService;
import com.mohit.academicerp.utility.EmployeeUtility;
import com.mohit.academicerp.utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
@Configurable
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeUtility employeeUtility;

    @Autowired
    private UserUtility userUtility;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addDepartment(@RequestBody Department department) {
        try {
            Boolean departmentExist = departmentService.departmentExists(department);
            if(Boolean.TRUE.equals(departmentExist)) {
                return ResponseEntity.status(500).body("Department name already exists.");
            }
            department.setName(department.getName().toUpperCase());
            Department result = departmentService.addDepartment(department);
            return ResponseEntity.of(Optional.of(result));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("There is an exception");
        }
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<?> getAllDepartments() {
        try {
            List<Department> result = departmentService.getAllDepartments();
            return ResponseEntity.of(Optional.of(result));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("There is an exception");
        }
    }

    @GetMapping(value = "/get/detail")
    public ResponseEntity<?> getDepartmentDetail() {
        try {
            List<Department> departmentList = departmentService.getAllDepartments();
            List<DetailsDTO> detailDTOList = new ArrayList<>();
            for (Department department : departmentList) {
                long count = employeeUtility.getDepartmentCount(department.getId());
                DetailsDTO detailDTO = new DetailsDTO();
                detailDTO.setName(department.getName());
                detailDTO.setCapacity(department.getCapacity());
                detailDTO.setStrength(count);
                detailDTOList.add(detailDTO);
            }
            return ResponseEntity.of(Optional.of(detailDTOList));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("There is an exception");
        }
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable int id) {
        try {
            boolean existDepartment = departmentService.existsDepartment(id);
            if (!existDepartment) {
                return ResponseEntity.status(500).body("Department does not exist.");
            }
            Optional<Department> result = departmentService.getDepartment(id);
            return ResponseEntity.of(Optional.of(result));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("There is an exception");
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable int id, @RequestBody Department department) {
        try {
            boolean existDepartment = departmentService.existsDepartment(id);
            if (!existDepartment) {
                return ResponseEntity.status(500).body("Department does not exist.");
            }
            Optional<Department> result = departmentService.getDepartment(id);
            if (result.isPresent() && result.get().getId() == id) {
                department.setId(result.get().getId());
                result = Optional.ofNullable(departmentService.addDepartment(department));
                return ResponseEntity.of(Optional.of(result));
            }
            return ResponseEntity.of(Optional.empty());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("There is an exception.");
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id) {
        try {
            boolean existDepartment = departmentService.existsDepartment(id);
            if (!existDepartment) {
                return ResponseEntity.of(Optional.of("{ 'status' : 'failure' }"));
            }
            Optional<Department> department = departmentService.getDepartment(7);
            List<Employee> employees = employeeUtility.getEmployeeByDept(id);
            for (Employee em : employees) {
                User user = userUtility.findUserByEmail(em.getEmail());
                user.setActive(false);
                user = userUtility.addUser(user);
                if (user == null || user.getId() == 0) {
                    return ResponseEntity.status(500).body("User could not be saved after deleting department.");
                }
                em.setDepartment(department.get());
                Employee temp = employeeUtility.addEmployee(em);
                if (temp == null || temp.getId() == 0) {
                    return ResponseEntity.status(500).body("Employee could not be saved after deleting department.");
                }
            }
            departmentService.deleteDepartment(id);
            return ResponseEntity.of(Optional.of("{ 'status' : 'success' }"));
        } catch (Exception e) {
            return ResponseEntity.of(Optional.of("{ 'status' : 'failure' }"));
        }
    }
}
