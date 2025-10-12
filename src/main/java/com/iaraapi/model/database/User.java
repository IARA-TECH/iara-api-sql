package com.iaraapi.model.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Table(name = "user_account")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @Column(name = "pk_uuid", updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    private String name;

    private String email;

    private String password;

    private Date dateOfBirth;

    @Column(name = "user_manager_uuid")
    private UUID userManagerId;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "factory_id")
    private Factory factory;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime changedAt;

    private LocalDateTime deactivatedAt;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UserAccessType> userAccessTypes = new HashSet<>();

    @JsonProperty("access_types")
    public Set<AccessType> getAccessTypes() {
        return userAccessTypes.stream()
                .map(UserAccessType::getAccessType)
                .collect(Collectors.toSet());
    }
}
