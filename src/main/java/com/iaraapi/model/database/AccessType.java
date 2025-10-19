package com.iaraapi.model.database;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "access_type")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Integer id;

    private String name;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime deactivatedAt;


}
