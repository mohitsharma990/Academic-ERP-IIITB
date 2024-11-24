package com.mohit.academicerp.dto;

import com.mohit.academicerp.entities.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotEmpty(message = "First name should not be empty")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotNull(message = "Active status should not be null")
    private Boolean active;

    @NotNull(message = "Expired status should not be null")
    private Boolean expired;

    @NotNull(message = "Employee should not be null")
    private Employee employee;
}