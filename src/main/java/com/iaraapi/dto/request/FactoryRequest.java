package com.iaraapi.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class FactoryRequest {
    @NotBlank(message = "CNPJ is required.")
    @Size(min = 14, max = 14, message = "CNPJ must have 14 characters")
    private String cnpj;

    @NotBlank(message = "Factory domain is required.")
    @Size(max = 20, message = "Domain must have a maximum of 20 characters")
    private String domain;

    @NotBlank(message = "Description is required.")
    @Size(max = 200, message = "Description must have a maximum of 200 characters")
    private String description;
}
