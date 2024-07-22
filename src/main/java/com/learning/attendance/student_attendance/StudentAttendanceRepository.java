package com.learning.attendance.student_attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Integer> {
    List<StudentAttendance> findByOrganizationId(UUID organizationId);
}
