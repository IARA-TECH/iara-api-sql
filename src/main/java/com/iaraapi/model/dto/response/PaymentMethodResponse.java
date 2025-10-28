package com.iaraapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentMethodResponse {
    private Integer id;
    private final String name;
}
