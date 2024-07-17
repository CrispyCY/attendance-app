package com.learning.attendance.purchase_package_transaction;

import com.learning.attendance.organization.Organization;
import com.learning.attendance.packages.Package;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "purchase_package_transactions")
public class PurchasePackageTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    UUID student_id;
    Timestamp created_at;
    Timestamp updated_at;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id")
    private Organization organization;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "package_id")
    private Package aPackage;

    public Integer getId() {
        return id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Package getAPackage() {
        return aPackage;
    }

    public void setAPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public UUID getStudent_id() {
        return student_id;
    }

    public void setStudent_id(UUID student_id) {
        this.student_id = student_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @PrePersist
    protected void onCreate() {
        created_at = Timestamp.from(Instant.now());
        updated_at = Timestamp.from(Instant.now());
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = Timestamp.from(Instant.now());
    }
}
