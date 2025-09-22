package com.iaraapi.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RoleRequest {
    @NotBlank(message = "Name is required.")
    @Size(max = 20, message = "Name must have a maximum of 20 characters")
    private String name;
}
