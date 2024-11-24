package com.mohit.academicerp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private String photoPath;

    @NotBlank(message = "Department is mandatory")
    private String department;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
