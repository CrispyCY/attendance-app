package com.learning.attendance.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Optional<Organization> getOrganizationById(UUID id) {
        return organizationRepository.findById(id);
    }

    public Organization createOrganization(Organization aOrganization) {
        return organizationRepository.save(aOrganization);
    }

    public Organization updateOrganization(UUID id, Organization organizationDetails) {
        Organization aOrganization = organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Organization not found"));

        aOrganization.setName(organizationDetails.getName());

        return organizationRepository.save(aOrganization);
    }

    public void deleteOrganization(UUID id) {
        Organization aOrganization = organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Organization not found"));
        organizationRepository.delete(aOrganization);
    }
}
