package com.learning.attendance.packages;

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
public class PackageService {
    private final PackageRepository packageRepository;
    private final UserRepository userRepository;

    @Autowired
    public PackageService(UserRepository userRepository, PackageRepository packageRepository) {
        this.userRepository = userRepository;
        this.packageRepository = packageRepository;
    }

    public List<Package> getAllPackages() {
        return packageRepository.findByIsActiveTrue();
    }

    public Optional<Package> getPackageById(UUID id) {
        return packageRepository.findById(id);
    }

    public Package createPackage(Package aPackage) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User authenticatedUser = this.userRepository.findByUsername(username);
        if (authenticatedUser == null) {
            throw new RuntimeException("Authenticated user not found");
        }

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
