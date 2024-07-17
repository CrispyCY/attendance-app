package com.learning.attendance.purchase_package_transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasePackageTransactionRepository extends JpaRepository<PurchasePackageTransaction, Integer> {
}
