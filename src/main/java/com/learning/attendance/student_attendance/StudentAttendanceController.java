package com.learning.attendance.student_attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/student-attendance")
public class StudentAttendanceController {
    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @GetMapping
    public List<CustomStudentAttendanceDTO> getAllAttendances() {
        return studentAttendanceService.getAllAttendances();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentAttendance> getAttendanceById(@PathVariable Integer id) {
        return studentAttendanceService.getAttendanceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentAttendance createAttendance(@RequestBody StudentAttendance aStudentAttendance) {
        return studentAttendanceService.createAttendance(aStudentAttendance);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PurchasePackageTransaction> updatePackage(@PathVariable Integer id, @RequestBody PurchasePackageTransaction purchasePackageTransactionDetails) {
//        return ResponseEntity.ok(purchasePackageTransactionService.updatePackage(id, purchasePackageTransactionDetails));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Integer id) {
        studentAttendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
