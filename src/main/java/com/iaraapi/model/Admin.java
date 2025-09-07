package com.iaraapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "admin_account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_uuid")
    private UUID id;

    @NotBlank(message = "Name is required.")
    @Size(max = 50, message = "Name has a maximum of 50 characters.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Size(max = 50, message = "Email has a maximum of 50 characters.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(max = 20, message = "Password has a maximum of 20 characters.")
    private String password;

    @NotNull(message = "Birth date is required.")
    private Date birthDate;

    @JoinColumn(name = "gender_id")
    @ManyToOne
    private Gender gender;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime changedAt;

    private LocalDateTime deactivatedAt;
}
