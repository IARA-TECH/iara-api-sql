package com.iaraapi.model.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAccessTypeRequest {
    @NotNull(message = "User ID is required.")
    private Integer accessTypeId;

    @NotNull(message = "Access Type ID is required.")
    private UUID userId;
}
