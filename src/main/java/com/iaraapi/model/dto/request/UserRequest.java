package com.iaraapi.model.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.util.UUID;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {
    @NotBlank(message = "Name is required.")
    @Size(max = 50, message = "Name must have a maximum of 20 characters.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Size(max = 50, message = "Email must have a maximum of 50 characters.")
    private String email;

    private String password;

    @NotNull(message = "Birthdate is required.")
    private Date dateOfBirth;

    private UUID userManagerId;

    @NotNull(message = "Factory is required.")
    private Integer factoryId;

    @NotNull(message = "Gender is required.")
    private Integer genderId;
}
