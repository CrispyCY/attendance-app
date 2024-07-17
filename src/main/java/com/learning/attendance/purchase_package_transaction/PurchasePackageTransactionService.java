package com.learning.attendance.purchase_package_transaction;

import com.learning.attendance.packages.Package;
import com.learning.attendance.packages.PackageRepository;
import com.learning.attendance.student.Student;
import com.learning.attendance.student.StudentRepository;
import com.learning.attendance.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchasePackageTransactionService {
    @Autowired
    private PurchasePackageTransactionRepository purchasePackageTransactionRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private StudentRepository studentRepository;

    public List<PurchasePackageTransaction> getAllTransacs() {
        return purchasePackageTransactionRepository.findAll();
    }

    public Optional<PurchasePackageTransaction> getTransacById(Integer id) {
        return purchasePackageTransactionRepository.findById(id);
    }

    public PurchasePackageTransaction createTransac(PurchasePackageTransaction aPurchasePackageTransaction) {
        Optional<Package> packageOpt = packageRepository.findById(aPurchasePackageTransaction.getAPackage().getId());

        if (packageOpt.isEmpty()) {
            throw new RuntimeException("Package not found with id: " + aPurchasePackageTransaction.getAPackage().getId());
        }

        Package aPackage = packageOpt.get();

        Optional<Student> studentOpt = studentRepository.findById(aPurchasePackageTransaction.getStudent_id());

        if (studentOpt.isEmpty()) {
            throw new RuntimeException("Student not found with id: " + aPurchasePackageTransaction.getStudent_id());
        }

        Student aStudent = studentOpt.get();

        studentService.updateSlotCount(aPurchasePackageTransaction.getStudent_id(), aStudent.getSlot_count() + aPackage.getSlot());

        return purchasePackageTransactionRepository.save(aPurchasePackageTransaction);
    }

    public void deleteTransac(Integer id) {
        PurchasePackageTransaction aPurchasePackageTransaction = purchasePackageTransactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
        purchasePackageTransactionRepository.delete(aPurchasePackageTransaction);
    }
}
