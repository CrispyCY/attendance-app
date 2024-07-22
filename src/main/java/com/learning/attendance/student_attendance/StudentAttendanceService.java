package com.learning.attendance.student_attendance;

import com.learning.attendance.auth.AuthUtils;
import com.learning.attendance.student.Student;
import com.learning.attendance.student.StudentRepository;
import com.learning.attendance.student.StudentService;
import com.learning.attendance.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentAttendanceService {
    private final StudentAttendanceRepository studentAttendanceRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final AuthUtils authUtils;

    @Autowired
    public StudentAttendanceService(StudentAttendanceRepository studentAttendanceRepository, StudentRepository studentRepository, StudentService studentService, AuthUtils authUtils) {
        this.studentAttendanceRepository = studentAttendanceRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
        this.authUtils = authUtils;
    }

    public List<CustomStudentAttendanceDTO> getAllAttendances() {
        User authenticatedUser = authUtils.getAuthenticatedUser();
        UUID organizationId = authenticatedUser.getOrganization().getId();

        List<StudentAttendance> attendances = studentAttendanceRepository.findByOrganizationId(organizationId);
        return attendances.stream()
                .map(CustomStudentAttendanceDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<StudentAttendance> getAttendanceById(Integer id) {
        return studentAttendanceRepository.findById(id);
    }

    public StudentAttendance createAttendance(StudentAttendance aStudentAttendance) {
        Optional<Student> studentOpt = studentRepository.findById(aStudentAttendance.getStudent().getId());

        if (studentOpt.isEmpty()) {
            throw new RuntimeException("Student not found with id: " + aStudentAttendance.getStudent().getId());
        }

        Student aStudent = studentOpt.get();

        studentService.updateSlotCount(aStudent.getId(), aStudent.getSlot_count() - 1);

        User authenticatedUser = authUtils.getAuthenticatedUser();
        aStudentAttendance.setOrganization(authenticatedUser.getOrganization());

        return studentAttendanceRepository.save(aStudentAttendance);
    }

    public void deleteAttendance(Integer id) {
        StudentAttendance aStudentAttendance = studentAttendanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
        studentAttendanceRepository.delete(aStudentAttendance);
    }
}
