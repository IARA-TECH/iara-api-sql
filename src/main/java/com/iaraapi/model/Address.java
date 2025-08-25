package com.iaraapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private int id;

    @NotBlank(message = "State is required.")
    @Size(max = 50, message = "State must have a maximum of 50 characters.")
    private String state;

    @NotBlank(message = "City is required.")
    @Size(max = 50, message = "City must have a maximum of 50 characters.")
    private String city;

    @NotBlank(message = "Neighbourhood is required.")
    @Size(max = 50, message = "Neighbourhood must have a maximum of 50 characters.")
    private String neighbourhood;

    @NotBlank(message = "CEP is required.")
    @Size(min = 8, max = 8, message = "CEP has only 8 characters.")
    private String cep;
    
    @NotNull(message = "Building number is required.")
    private Integer buildingNumber;

    @NotBlank(message = "Street is required.")
    @Size(max = 50, message = "Street must have a maximum of 50 characters.")
    private String street;

    @NotBlank(message = "State is required.")
    @Size(max = 20, message = "State must have a maximum of 30 characters.")
    private String complement;

}
