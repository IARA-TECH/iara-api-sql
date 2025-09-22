package com.iaraapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Long id;

    private String state;

    private String city;

    private String neighbourhood;

    private String cep;
    
    private Integer buildingNumber;

    private String street;

    private String complement;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Factory factory;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
