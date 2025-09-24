package com.iaraapi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "Name is required.")
    @Size(max = 50, message = "Name must have a maximum of 20 characters.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Size(max = 50, message = "Email must have a maximum of 50 characters.")
    private String email;

    private String password;

    @NotNull(message = "Birthdate is required.")
    private Date birthDate;

    @NotNull(message = "Position is required")
    private String position;

    @NotNull(message = "Access level is required.")
    @Min(value = 1, message = "Access level must be greater than 0.")
    private Integer accessLevel;

    @NotNull(message = "Gender is required")
    private Long genderId;
}
