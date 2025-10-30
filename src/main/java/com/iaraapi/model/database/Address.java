package com.iaraapi.model.database;

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
    private Integer id;

    private String state;

    private String city;

    private String neighborhood;

    private String cep;
    
    private Integer buildingNumber;

    private String street;

    private String complement;

    @ManyToOne
    @JoinColumn(name = "factory_id")
    private Factory factory;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
