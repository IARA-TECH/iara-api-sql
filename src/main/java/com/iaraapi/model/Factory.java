package com.iaraapi.model.database;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "factory")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Long id;

    @NotBlank(message = "CNPJ is required.")
    @Size(min = 14, max = 14, message = "CNPJ must have 14 characters")
    private String cnpj;

    @NotBlank(message = "Factory domain is required.")
    @Size(max = 20, message = "Domain must have a maximum of 20 characters")
    private String domain;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime deactivatedAt;
}
