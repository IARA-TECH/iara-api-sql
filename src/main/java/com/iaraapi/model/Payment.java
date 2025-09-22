package com.iaraapi.model.database;


import jakarta.persistence.*;
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

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private LocalDateTime expiresOn;

    @Column(nullable = false)
    private Boolean isExpired;

    @ManyToOne
    @JoinColumn(name = "subscription_id",  nullable = false)
    private Subscription subscription;


    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @CreationTimestamp
    private LocalDateTime paidAt;


}
