package com.iaraapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "shift")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Long id;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull(message = "Start time is required.")
    private LocalTime startsAt;

    @NotNull(message = "End time is required.")
    private LocalTime endsAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime changedAt;
}
