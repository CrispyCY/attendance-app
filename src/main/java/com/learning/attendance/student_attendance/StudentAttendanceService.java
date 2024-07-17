package com.learning.attendance.student_attendance;

import com.learning.attendance.student.Student;
import com.learning.attendance.student.StudentRepository;
import com.learning.attendance.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentAttendanceService {
    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    public List<CustomStudentAttendanceDTO> getAllAttendances() {
        List<StudentAttendance> attendances = studentAttendanceRepository.findAll();
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

        return studentAttendanceRepository.save(aStudentAttendance);
    }

    public void deleteAttendance(Integer id) {
        StudentAttendance aStudentAttendance = studentAttendanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
        studentAttendanceRepository.delete(aStudentAttendance);
    }
}
