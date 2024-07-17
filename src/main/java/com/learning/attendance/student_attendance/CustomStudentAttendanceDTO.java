package com.learning.attendance.student_attendance;

import java.sql.Timestamp;
import java.util.UUID;

public class CustomStudentAttendanceDTO {
    private Integer id;
    private UUID orgId;
    private String orgName;
    private UUID classId;
    private String className;
    private UUID studentId;
    private String studentName;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructor, getters, and setters
    public CustomStudentAttendanceDTO(StudentAttendance attendance) {
        this.id = attendance.getId();
        this.orgId = attendance.getOrganization().getId();
        this.orgName = attendance.getOrganization().getName();
        this.classId = attendance.getAClass().getId();
        this.className = attendance.getAClass().getName();
        this.studentId = attendance.getStudent().getId();
        this.studentName = attendance.getStudent().getName();
        this.createdAt = attendance.getCreated_at();
        this.updatedAt = attendance.getUpdated_at();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getOrgId() {
        return orgId;
    }

    public void setOrgId(UUID orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public UUID getClassId() {
        return classId;
    }

    public void setClassId(UUID classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
