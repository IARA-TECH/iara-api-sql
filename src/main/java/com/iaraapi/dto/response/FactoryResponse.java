package com.iaraapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FactoryResponse {
    private String cnpj;

    private String domain;

    private String description;
}
