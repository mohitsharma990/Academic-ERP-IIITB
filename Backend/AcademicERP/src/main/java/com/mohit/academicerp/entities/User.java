package com.mohit.academicerp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "expired", nullable = false)
    private Boolean expired;

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = true)
    private Employee employee;
}