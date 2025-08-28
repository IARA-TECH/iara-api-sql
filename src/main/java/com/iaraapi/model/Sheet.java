package com.iaraapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

public class Sheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Blob URL is required.")
    @Size(max = 100, message = "The URL must have a maximum of 100 characters.")
    private String urlBlob;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    @NotNull(message = "Shift is required.")
    private Shift shift;

    @NotNull(message = "Reference date is required.")
    private LocalDateTime referenceDate;

    @ManyToOne
    @JoinColumn(name = "created_by")
    @NotNull(message = "Creator is required.")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "validated_by")
    private User validatedBy;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
