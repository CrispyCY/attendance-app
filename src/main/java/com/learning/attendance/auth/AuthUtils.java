package com.learning.attendance.auth;

import com.learning.attendance.user.User;
import com.learning.attendance.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    private final UserRepository userRepository;

    public AuthUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User authenticatedUser = userRepository.findByUsername(username);
        if (authenticatedUser == null) {
            throw new RuntimeException("Authenticated user not found");
        }

        return authenticatedUser;
    }
}
