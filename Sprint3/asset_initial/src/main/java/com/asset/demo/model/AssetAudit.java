package com.asset.demo.model;

import com.asset.demo.enums.AuditStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "asset_audit")
public class AssetAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ---------------- ASSET ----------------
    @NotNull(message = "Asset is required")
    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    // ---------------- EMPLOYEE ----------------
    @NotNull(message = "Employee is required")
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private User employee;

    // ---------------- AUDIT DATE ----------------
    @CreationTimestamp
    @Column(name = "audit_date", updatable = false)
    private Instant auditDate;

    // ---------------- STATUS ----------------
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private AuditStatus status = AuditStatus.PENDING;

    // ---------------- REMARKS ----------------
    @Size(max = 1000)
    @Column(columnDefinition = "TEXT")
    private String remarks;
}