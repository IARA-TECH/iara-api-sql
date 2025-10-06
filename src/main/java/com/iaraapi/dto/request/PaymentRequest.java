package com.iaraapi.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentRequest {

    @NotNull(message = "User Account UUID is required.")
    private UUID userAccountUid;

    @NotNull(message = "Subscription ID is required.")
    private Integer subscriptionId;

    @NotNull(message = "Payment Method ID is required.")
    private Integer paymentMethodId;
}
