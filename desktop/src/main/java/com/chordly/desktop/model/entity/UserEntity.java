package com.chordly.desktop.model.entity;

public class UserEntity {
    private Long id;
    private String username;
    private String email;
    private String role;

    public UserEntity() {
        role = "default";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "User: " + username + " (" + email + ")";
    }
}