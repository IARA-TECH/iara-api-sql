package com.iaraapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PaymentResponse {
    private BigDecimal total;
    private LocalDate expiresOn;
    private LocalDate startsOn;
    private String paymentMethodName;
    private String subscriptionName;
    private String userName;
}
