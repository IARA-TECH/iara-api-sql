package com.iaraapi.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface FactoryActivePayment {
    Integer getPkId();
    String getSubscriptionName();
    LocalDate getStartsAt();
    LocalDate getExpiresOn();
    BigDecimal getTotal();
}
