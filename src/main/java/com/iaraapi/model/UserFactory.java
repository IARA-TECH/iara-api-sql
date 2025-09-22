package com.iaraapi.model.database;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public class UserFactory {
    @JoinColumn(nullable = false)
    @ManyToOne
    User user;

    @JoinColumn(nullable = false)
    @ManyToOne
    Factory factory;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
