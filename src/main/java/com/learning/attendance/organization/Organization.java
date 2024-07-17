package com.learning.attendance.organization;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "organizations")
public class Organization {
    @Id
    UUID id;
    String name;
    String email;
    String address;
    String phone_no1;
    String phone_no2;
    Boolean is_active;
    java.sql.Timestamp created_at;
    java.sql.Timestamp updated_at;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_no1() {
        return phone_no1;
    }

    public void setPhone_no1(String phone_no1) {
        this.phone_no1 = phone_no1;
    }

    public String getPhone_no2() {
        return phone_no2;
    }

    public void setPhone_no2(String phone_no2) {
        this.phone_no2 = phone_no2;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
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
