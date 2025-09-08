package com.iaraapi.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SusbscriptionRequest {
    @NotNull(message = "Price is required.")
    private Double price;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull(message = "Monthly duration is required.")
    private Integer monthlyDuration;
}
