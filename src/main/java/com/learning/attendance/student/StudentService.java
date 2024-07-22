package com.learning.attendance.student;

import com.learning.attendance.auth.AuthUtils;
import com.learning.attendance.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final AuthUtils authUtils;

    @Autowired
    public StudentService(StudentRepository studentRepository, AuthUtils authUtils) {
        this.studentRepository = studentRepository;
        this.authUtils = authUtils;
    }

    public List<Student> getAllStudents() {
        User authenticatedUser = authUtils.getAuthenticatedUser();

        UUID organizationId = authenticatedUser.getOrganization().getId();
        return studentRepository.findByIsActiveTrueAndOrganizationId(organizationId);
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        User authenticatedUser = authUtils.getAuthenticatedUser();

        student.setOrganization(authenticatedUser.getOrganization());
        student.setSlot_count(0);
        student.setActive(true);

        return studentRepository.save(student);
    }

    public Student updateStudent(UUID id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setSlot_count(studentDetails.getSlot_count());
        student.setAddress(studentDetails.getAddress());
        student.setDob(studentDetails.getDob());

        return studentRepository.save(student);
    }

    public void deleteStudent(UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setActive(false);

        studentRepository.save(student);
    }

    public void updateSlotCount(UUID id, Integer newCount) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setSlot_count(newCount);

        studentRepository.save(student);
    }
}