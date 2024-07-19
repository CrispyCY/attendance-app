package com.learning.attendance.student;

import com.learning.attendance.user.User;
import com.learning.attendance.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(UserRepository userRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findByIsActiveTrue();
    }

    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User authenticatedUser = userRepository.findByUsername(username);
        if (authenticatedUser == null) {
            throw new RuntimeException("Authenticated user not found");
        }

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