package com.mohit.academicerp.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailsDTO {

    private String name;

    @Positive(message = "Capacity must be positive")
    private long capacity;

    @Positive(message = "Strength must be positive")
    private long strength;

    // Optional: Constructor to easily initialize all fields
    public DetailsDTO(String name, long capacity, long strength) {
        this.name = name;
        this.capacity = capacity;
        this.strength = strength;
    }

}