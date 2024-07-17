package com.learning.attendance.user;

import java.util.UUID;

public class RegisterUserDTO {
    private String email;

    private String password;

    private String username;
    private UUID org_id;

    public UUID getOrg_id() {
        return org_id;
    }

    public void setOrg_id(UUID org_id) {
        this.org_id = org_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
