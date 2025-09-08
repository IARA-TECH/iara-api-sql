package com.iaraapi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
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
    private LocalDateTime paidAt;


}
