package com.iaraapi.model.database;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_access_type")
@Data
public class UserAccessType {

    @EmbeddedId
    private UserAccessTypeId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_account_uuid")
    private User user;

    @ManyToOne
    @MapsId("accessTypeId")
    @JoinColumn(name = "access_type_id")
    private AccessType accessType;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime deactivatedAt;
}
