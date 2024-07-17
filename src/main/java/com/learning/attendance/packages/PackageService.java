package com.learning.attendance.packages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    public Optional<Package> getPackageById(UUID id) {
        return packageRepository.findById(id);
    }

    public Package createPackage(Package aPackage) {
        return packageRepository.save(aPackage);
    }

    public Package updatePackage(UUID id, Package packageDetails) {
        Package aPackage = packageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));

        aPackage.setName(packageDetails.getName());

        return packageRepository.save(aPackage);
    }

    public void deletePackage(UUID id) {
        Package aPackage = packageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
        packageRepository.delete(aPackage);
    }
}
