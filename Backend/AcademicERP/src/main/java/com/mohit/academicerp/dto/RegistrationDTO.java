package com.mohit.academicerp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegistrationDTO {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Path is mandatory")
    private String path;

    @NotBlank(message = "Department is mandatory")
    private String department;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
