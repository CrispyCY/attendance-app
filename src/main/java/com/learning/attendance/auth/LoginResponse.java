package com.learning.attendance.auth;

import com.learning.attendance.user.User;

public class LoginResponse {
    private String token;
    private long expiresIn;
    private User user;

    // Getters and setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Fluent setters for method chaining
    public LoginResponse withToken(String token) {
        this.token = token;
        return this;
    }

    public LoginResponse withExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public LoginResponse withUser(User user) {
        this.user = user;
        return this;
    }
}
