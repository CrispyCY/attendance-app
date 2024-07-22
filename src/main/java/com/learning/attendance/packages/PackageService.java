package com.learning.attendance.packages;

import com.learning.attendance.auth.AuthUtils;
import com.learning.attendance.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PackageService {
    private final PackageRepository packageRepository;
    private final AuthUtils authUtils;

    @Autowired
    public PackageService(PackageRepository packageRepository, AuthUtils authUtils) {
        this.packageRepository = packageRepository;
        this.authUtils = authUtils;
    }

    public List<Package> getAllPackages() {
        User authenticatedUser = authUtils.getAuthenticatedUser();
        UUID organizationId = authenticatedUser.getOrganization().getId();


        return packageRepository.findByOrganizationId(organizationId);
    }

    public Optional<Package> getPackageById(UUID id) {
        return packageRepository.findById(id);
    }

    public Package createPackage(Package aPackage) {
        User authenticatedUser = authUtils.getAuthenticatedUser();

        aPackage.setOrganization(authenticatedUser.getOrganization());
        aPackage.setActive(true);
        return packageRepository.save(aPackage);
    }

    public Package updatePackage(UUID id, Package packageDetails) {
        Package aPackage = packageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));

        aPackage.setName(packageDetails.getName());
        aPackage.setPrice(packageDetails.getPrice());
        aPackage.setSlot(packageDetails.getSlot());
        aPackage.setExpiry_days(packageDetails.getExpiry_days());

        return packageRepository.save(aPackage);
    }

    public void deletePackage(UUID id) {
        Package aPackage = packageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
        aPackage.setActive(false);

        packageRepository.save(aPackage);
    }
}
