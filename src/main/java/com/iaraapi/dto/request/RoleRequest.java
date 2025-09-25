package com.iaraapi.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoleRequest {
    @NotBlank(message = "Name is required.")
    @Size(max = 20, message = "Name must have a maximum of 20 characters")
    private String name;

    @NotNull(message = "Factory ID is required.")
    private String factoryId;
}
