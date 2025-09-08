package com.iaraapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class SubscriptionResponse {
    private Double price;
    private String name;
    private Integer monthlyDuration;
}
