package com.mohit.academicerp.repositories;

import com.mohit.academicerp.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM employee e JOIN e.department d WHERE d.id = ?1")
    List<Employee> findByDepartmentId(int id);

    @Query("SELECT e FROM employee e WHERE e.email = ?1")
    Optional<Employee> findByEmail(String email);

    @Query("SELECT count(e) FROM employee e WHERE e.department.id = ?1")
    int countByDepartmentId(int id);
}