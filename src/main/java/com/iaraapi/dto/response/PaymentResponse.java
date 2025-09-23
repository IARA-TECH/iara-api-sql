package com.iaraapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PaymentResponse {
    private BigDecimal total;
    private LocalDateTime expiresOn;
    private String paymentMethod;
    private String subscription;
}
