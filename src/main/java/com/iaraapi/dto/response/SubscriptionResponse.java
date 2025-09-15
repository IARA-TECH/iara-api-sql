package com.iaraapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class SubscriptionResponse {
    private BigDecimal price;
    private String name;
    private Integer monthlyDuration;
}
