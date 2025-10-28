package com.iaraapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class PaymentMethodResponse {
    private Integer id;
    private final String name;
}
