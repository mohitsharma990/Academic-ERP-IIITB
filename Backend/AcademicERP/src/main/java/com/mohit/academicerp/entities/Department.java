package com.mohit.academicerp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    @Size(min = 2, max = 50, message = "Department name must be between 2 and 50 characters")
    private String name;

    @Column(name = "capacity", nullable = false)
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;
}