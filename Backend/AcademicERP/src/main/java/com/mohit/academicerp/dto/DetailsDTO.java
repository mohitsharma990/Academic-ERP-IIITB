package com.mohit.academicerp.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailsDTO {

    private String name;

    @Positive(message = "Capacity must be positive")
    private long capacity;

    @Positive(message = "Strength must be positive")
    private long strength;

}