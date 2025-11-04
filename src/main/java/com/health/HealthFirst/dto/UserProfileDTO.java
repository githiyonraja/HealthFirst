package com.health.HealthFirst.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserProfileDTO {
    @NotBlank
    public String firstname;
    @NotBlank
    public String lastname;
    @NotNull
    @Min(0)
    @Max(150)
    public Integer age;
    // Expecting 'M'/'F' or similar single-char markers
    @NotNull
    public Character gender;
    @Email
    public String email;
}


