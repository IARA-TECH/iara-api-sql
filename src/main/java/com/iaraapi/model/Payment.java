package com.iaraapi.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Total is required.")
    private Double total;

    @NotNull(message = "Expiration date is required.")
    private LocalDateTime expiresOn;

    @NotNull(message = "Indicate if the payment has expired.")
    private Boolean isExpired;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    @NotNull(message = "Subscription is required.")
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "admin_account_uid")
    @NotNull(message = "Admin is required.")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    @NotNull(message = "Payment method is required.")
    private PaymentMethod paymentMethod;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime paidAt;


}
