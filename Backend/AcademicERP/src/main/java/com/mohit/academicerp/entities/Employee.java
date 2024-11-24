package com.mohit.academicerp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "first_name", nullable = false)
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 50, message = "Last name must be up to 50 characters")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Invalid email address")
    private String email;

    @Column(name = "title")
    private String title;

    @Column(name = "photo_path")
    private String photoPath;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}