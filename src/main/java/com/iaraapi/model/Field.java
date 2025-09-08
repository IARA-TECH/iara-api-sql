package com.iaraapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "field")
@AllArgsConstructor
@NoArgsConstructor
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Long id;

    @NotBlank(message = "State is required.")
    @Size(max = 20, message = "State must have a maximum of 50 characters.")
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
