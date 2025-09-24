package com.iaraapi.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public class UserRole {

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_account_uuid")
    User user;

    @ManyToOne
    @MapsId("factoryId")
    @JoinColumn(name = "factory_id")
    Factory factory;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    Role role;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
