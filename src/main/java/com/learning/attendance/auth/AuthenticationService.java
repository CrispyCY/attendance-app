package com.learning.attendance.auth;

import com.learning.attendance.organization.Organization;
import com.learning.attendance.organization.OrganizationRepository;
import com.learning.attendance.user.LoginUserDTO;
import com.learning.attendance.user.RegisterUserDTO;
import com.learning.attendance.user.User;
import com.learning.attendance.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final OrganizationRepository organizationRepository;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            OrganizationRepository organizationRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.organizationRepository = organizationRepository;
    }

    public User signup(RegisterUserDTO input) {
        Optional<Organization> orgOptional = this.organizationRepository.findById(input.getOrg_id());
        if (orgOptional.isEmpty()) {
            throw new RuntimeException("Org not found with id: " + input.getOrg_id());
        }
        Organization org = orgOptional.get();
        User user = new User()
                .setUsername(input.getUsername())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setOrganization(org);

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}