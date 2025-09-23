package com.iaraapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class PaymentResponse {
    private Double total;
    private LocalDateTime expiresOn;
    private String paymentMethod;
    private String subscription;
}
