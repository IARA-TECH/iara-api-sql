package com.iaraapi.model.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "daily_active_users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DailyActiveUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Integer id;

    @CreationTimestamp
    private LocalDateTime accessedOn;

    @ManyToOne
    @JoinColumn(name = "user_account_uuid")
    private User user;
}
