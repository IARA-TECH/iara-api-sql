package com.iaraapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddressResponse {
    private String state;

    private String city;

    private String neighborhood;

    private String cep;

    private Integer buildingNumber;

    private String street;

    private String complement;

    private String factoryName;
}
