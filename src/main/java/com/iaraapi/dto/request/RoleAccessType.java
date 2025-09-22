package com.iaraapi.model.database;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class RoleAccessType {
    @ManyToOne
    @JoinColumn(nullable = false)
    private RoleAccessType role;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AccessType accessType;

}
