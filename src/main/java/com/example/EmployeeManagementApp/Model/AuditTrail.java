package com.example.EmployeeManagementApp.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuditTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action; // e.g., CREATE, UPDATE, DELETE
    private String entity; // e.g., Employee
    private String entityId;
    private String changedBy; // Who made the change
    private LocalDateTime timestamp; // When the change was made

    // Constructor, Getters, and Setters
    public AuditTrail(String action, String entity, String entityId, String changedBy, LocalDateTime timestamp) {
        this.action = action;
        this.entity = entity;
        this.entityId = entityId;
        this.changedBy = changedBy;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    // ...
}
