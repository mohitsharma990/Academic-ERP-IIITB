package com.mohit.academicerp.repositories;

import com.mohit.academicerp.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT e FROM employee e JOIN e.department d WHERE d.id = :id", nativeQuery = true)
    List<Employee> findByDepartmentId(@Param("id") int id);

    @Query(value = "SELECT * FROM employee WHERE email = :email", nativeQuery = true)
    Employee findByEmail( @Param("email") String email);

    @Query(value = "SELECT count(*) FROM employee WHERE department = :id", nativeQuery = true)
    int countByDepartmentId(@Param("id") int id);
}