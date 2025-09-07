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

    @NotNull(message = "Indicate if the payment has expired.")
    private Boolean isExpired;

    @NotNull(message = "Admin ID is required.")
    private UUID adminAccountUid;

    @NotNull(message = "Payment method is required.")
    private Long paymentMethodId;
}
