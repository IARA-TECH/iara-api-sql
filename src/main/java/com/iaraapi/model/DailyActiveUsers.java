package com.iaraapi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public class DailyActiveUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime accessedOn;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
}
