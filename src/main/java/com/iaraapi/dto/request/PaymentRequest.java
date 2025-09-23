package com.iaraapi.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentRequest {
    @NotNull(message = "Total is required.")
    private Double total;

    @NotNull(message = "Expiration date is required.")
    private LocalDateTime expiresOn;

    @NotNull(message = "User ID is required.")
    private UUID userAccountUid;

    @NotNull(message = "Payment method is required.")
    private Long paymentMethodId;

    @NotNull(message = "Subscription is required.")
    private Long subscriptionId;

    @NotNull(message = "Factory ID is required.")
    private Long factoryId;
}
