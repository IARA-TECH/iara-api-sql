package com.iaraapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "gender")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Integer id;

    private String name;

    private Timestamp createdAt;
}
