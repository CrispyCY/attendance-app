package com.learning.attendance.purchase_package_transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/package-transac")
public class PurchasePackageTransactionController {
    private final PurchasePackageTransactionService purchasePackageTransactionService;

    @Autowired
    public PurchasePackageTransactionController(PurchasePackageTransactionService purchasePackageTransactionService) {
        this.purchasePackageTransactionService = purchasePackageTransactionService;
    }

    @GetMapping
    public List<PurchasePackageTransaction> getAllTransacs() {
        return purchasePackageTransactionService.getAllTransacs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchasePackageTransaction> getTransacById(@PathVariable Integer id) {
        return purchasePackageTransactionService.getTransacById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping
//    public PurchasePackageTransaction createTransac(@RequestBody PurchasePackageTransaction aPurchasePackageTransaction) {
//        return purchasePackageTransactionService.createTransac(aPurchasePackageTransaction);
//    }

    @PostMapping
    public List<PurchasePackageTransaction> createTransac(@RequestBody PurchasePackageTransactionRequestDTO dto) {
        return purchasePackageTransactionService.createTransac(dto);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PurchasePackageTransaction> updatePackage(@PathVariable Integer id, @RequestBody PurchasePackageTransaction purchasePackageTransactionDetails) {
//        return ResponseEntity.ok(purchasePackageTransactionService.updatePackage(id, purchasePackageTransactionDetails));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransac(@PathVariable Integer id) {
        purchasePackageTransactionService.deleteTransac(id);
        return ResponseEntity.noContent().build();
    }
}
