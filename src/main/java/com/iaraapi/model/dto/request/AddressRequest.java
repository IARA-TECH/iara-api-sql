package com.iaraapi.model.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressRequest {
    @NotBlank(message = "State is required.")
    @Size(max = 50, message = "State must have a maximum of 50 characters.")
    private String state;

    @NotBlank(message = "City is required.")
    @Size(max = 50, message = "City must have a maximum of 50 characters.")
    private String city;

    @NotBlank(message = "Neighbourhood is required.")
    @Size(max = 50, message = "Neighbourhood must have a maximum of 50 characters.")
    private String neighborhood;

    @NotBlank(message = "CEP is required.")
    @Size(min = 8, max = 8, message = "CEP has only 8 characters.")
    private String cep;

    @NotNull(message = "Building number is required.")
    private Integer buildingNumber;

    @NotBlank(message = "Street is required.")
    @Size(max = 50, message = "Street must have a maximum of 50 characters.")
    private String street;

    @NotBlank(message = "Complement is required.")
    @Size(max = 255, message = "Complement must have a maximum of 255 characters.")
    private String complement;

    @NotNull(message = "Factory is required.")
    private Integer factoryId;

}
