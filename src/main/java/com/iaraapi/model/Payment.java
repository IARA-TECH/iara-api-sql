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

    private Double total;

    private LocalDateTime expiresOn;

    private Boolean isExpired;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;


    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "user_account_uuid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "factory_id")
    private Factory factory;

    @CreationTimestamp
    private LocalDateTime startsAt;
}
