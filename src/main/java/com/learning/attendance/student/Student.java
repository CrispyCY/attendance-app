package com.learning.attendance.student;

import com.learning.attendance.organization.Organization;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    UUID id;
    UUID group_id;
    String name;
    String email;
    java.sql.Date dob;
    String address;
    String phone_no1;
    String phone_no2;
    @Column(name = "is_active")
    Boolean isActive;
    Integer slot_count;
    java.sql.Date slot_expiry_date;
    java.sql.Timestamp created_at;
    java.sql.Timestamp updated_at;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id")
    private Organization organization;

    public UUID getId() {
        return id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public UUID getGroup_id() {
        return group_id;
    }

    public void setGroup_id(UUID group_id) {
        this.group_id = group_id;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getSlot_count() {
        return slot_count;
    }

    public void setSlot_count(Integer slot_count) {
        this.slot_count = slot_count;
    }

    public Date getSlot_expiry_date() {
        return slot_expiry_date;
    }

    public void setSlot_expiry_date(Date slot_expiry_date) {
        this.slot_expiry_date = slot_expiry_date;
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
