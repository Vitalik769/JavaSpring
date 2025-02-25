package com.yaremenko.demo4.models;

import jakarta.validation.constraints.NotBlank;

public class User {
    @NotBlank(message = "Ім'я користувача не може бути порожнім")
    private String username;
    private String role;

    public User() {}

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
