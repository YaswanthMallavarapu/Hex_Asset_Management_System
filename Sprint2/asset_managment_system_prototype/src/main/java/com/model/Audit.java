package com.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "audit_date", nullable = false)
    private LocalDate auditDate;

    public Audit() {
    }

    public Audit(LocalDate auditDate) {
        this.auditDate = auditDate;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(LocalDate auditDate) {
        this.auditDate = auditDate;
    }
}