package com.learning.attendance.purchase_package_transaction;

import com.learning.attendance.packages.Package;
import com.learning.attendance.packages.PackageRepository;
import com.learning.attendance.student.Student;
import com.learning.attendance.student.StudentRepository;
import com.learning.attendance.student.StudentService;
import com.learning.attendance.user.User;
import com.learning.attendance.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchasePackageTransactionService {
    private final PurchasePackageTransactionRepository purchasePackageTransactionRepository;
    private final StudentService studentService;
    private final PackageRepository packageRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Autowired
    public PurchasePackageTransactionService(PurchasePackageTransactionRepository purchasePackageTransactionRepository, StudentService studentService, PackageRepository packageRepository, StudentRepository studentRepository, UserRepository userRepository) {
        this.purchasePackageTransactionRepository = purchasePackageTransactionRepository;
        this.studentService = studentService;
        this.packageRepository = packageRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    public List<PurchasePackageTransaction> getAllTransacs() {
        return purchasePackageTransactionRepository.findAll();
    }

    public Optional<PurchasePackageTransaction> getTransacById(Integer id) {
        return purchasePackageTransactionRepository.findById(id);
    }

//    public PurchasePackageTransaction createTransac(PurchasePackageTransaction aPurchasePackageTransaction) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//
//        User authenticatedUser = this.userRepository.findByUsername(username);
//        if (authenticatedUser == null) {
//            throw new RuntimeException("Authenticated user not found");
//        }
//
//        Optional<Package> packageOpt = packageRepository.findById(aPurchasePackageTransaction.getAPackage().getId());
//
//        if (packageOpt.isEmpty()) {
//            throw new RuntimeException("Package not found with id: " + aPurchasePackageTransaction.getAPackage().getId());
//        }
//
//        Package aPackage = packageOpt.get();
//
//        Optional<Student> studentOpt = studentRepository.findById(aPurchasePackageTransaction.getStudent().getId());
//
//        if (studentOpt.isEmpty()) {
//            throw new RuntimeException("Student not found with id: " + aPurchasePackageTransaction.getStudent().getId());
//        }
//
//        Student aStudent = studentOpt.get();
//
//        studentService.updateSlotCount(aStudent.getId(), aStudent.getSlot_count() + aPackage.getSlot());
//        aPurchasePackageTransaction.setAPackage(aPackage);
//        aPurchasePackageTransaction.setStudent(aStudent);
//        aPurchasePackageTransaction.setOrganization(authenticatedUser.getOrganization());
//
//        return purchasePackageTransactionRepository.save(aPurchasePackageTransaction);
//    }

    public List<PurchasePackageTransaction> createTransac(PurchasePackageTransactionRequestDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User authenticatedUser = this.userRepository.findByUsername(username);
        if (authenticatedUser == null) {
            throw new RuntimeException("Authenticated user not found");
        }

        Optional<Package> packageOpt = packageRepository.findById(dto.getPackageId());
        if (packageOpt.isEmpty()) {
            throw new RuntimeException("Package not found with id: " + dto.getPackageId());
        }

        Package aPackage = packageOpt.get();

        List<PurchasePackageTransaction> transactions = new ArrayList<>();

        for (UUID studentId : dto.getStudentIds()) {
            Optional<Student> studentOpt = studentRepository.findById(studentId);
            if (studentOpt.isEmpty()) {
                throw new RuntimeException("Student not found with id: " + studentId);
            }

            Student aStudent = studentOpt.get();
            studentService.updateSlotCount(studentId, aStudent.getSlot_count() + aPackage.getSlot());

            PurchasePackageTransaction transaction = new PurchasePackageTransaction();
            transaction.setAPackage(aPackage);
            transaction.setStudent(aStudent);
            transaction.setOrganization(authenticatedUser.getOrganization());
            transactions.add(purchasePackageTransactionRepository.save(transaction));
        }

        return transactions;
    }

    public void deleteTransac(Integer id) {
        PurchasePackageTransaction aPurchasePackageTransaction = purchasePackageTransactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
        purchasePackageTransactionRepository.delete(aPurchasePackageTransaction);
    }
}
