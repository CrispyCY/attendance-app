package com.learning.attendance.purchase_package_transaction;

import java.util.List;
import java.util.UUID;

public class PurchasePackageTransactionRequestDTO {
    private UUID packageId;
    private List<UUID> studentIds;

    // Getters and Setters
    public UUID getPackageId() {
        return packageId;
    }

    public void setPackageId(UUID packageId) {
        this.packageId = packageId;
    }

    public List<UUID> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<UUID> studentIds) {
        this.studentIds = studentIds;
    }
}