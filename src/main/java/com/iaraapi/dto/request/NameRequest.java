package com.iaraapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NameRequest {
    @NotBlank(message = "Name is required.")
    private String name;
}
