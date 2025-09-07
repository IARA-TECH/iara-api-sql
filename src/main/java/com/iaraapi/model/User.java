package com.iaraapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "user_account")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pk_uuid")
    private UUID id;

    @NotBlank(message = "Name is required.")
    @Size(max = 50, message = "Name must have a maximum of 20 characters.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Size(max = 50, message = "Email must have a maximum of 50 characters.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 20, message = "Password must have a minimum of 8 and a maximum of 20 characters.")
    private String password;

    @NotNull(message = "Birthdate is required.")
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    @NotNull(message = "Gender is required.")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull(message = "Role is required.")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "factory_id")
    @NotNull(message = "Factory is required.")
    private Factory factory;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime changedAt;
    private LocalDateTime deactivatedAt;


}
