package com.iaraapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
pk_id SERIAL PRIMARY KEY,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
cnpj VARCHAR(14) NOT NULL,
domain VARCHAR(20) NOT NULL,
company_id INT NOT NULL,
admin_account_uuid UUID NOT NULL,
address_id INT NOT NULL
*/

@Entity
@Table(name = "factory")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Integer id;

    @NotBlank(message = "CNPJ is required.")
    @Size(min = 14, max = 14, message = "CNPJ must have 14 characters")
    private String cnpj;

    @NotBlank(message = "Factory domain is required.")
    @Size(max = 20, message = "Domain must have a maximum of 20 characters")
    private String domain;

//    @ManyToOne
//    @JoinColumn(name = "company_id")
//    @NotNull(message = "Company is required.")
//    private Company company;

    @ManyToOne
    @JoinColumn(name = "admin_account_uuid")
    @NotNull(message = "Admin is required.")
    private Admin admin;

//    @ManyToOne
//    @JoinColumn(name = "address_id")
//    @NotNull(message = "Address is required.")
//    private Address address;

    private LocalDate createdAt;
}
