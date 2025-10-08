package com.iaraapi.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DailyActiveUsersRequest {
    @NotNull(message = "User ID is required.")
    private UUID userId;
}
