package com.iaraapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "role_access_type")
@Data
public class RoleAccessType {

    @EmbeddedId
    private RoleAccessTypeId id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @MapsId("accessTypeId")
    @JoinColumn(name = "access_type_id")
    private AccessType accessType;

    private LocalDateTime createdAt;
    private LocalDateTime deactivatedAt;
}
