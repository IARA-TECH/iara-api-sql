package com.iaraapi.model.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SubscriptionRequest {
    @NotNull(message = "Price is required.")
    private BigDecimal price;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank
    private String description;

    @NotNull(message = "Monthly duration is required.")
    private Integer monthlyDuration;
}
