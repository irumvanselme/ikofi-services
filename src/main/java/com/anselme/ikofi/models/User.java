package com.anselme.ikofi.models;

import com.sun.istack.NotNull;
import com.anselme.ikofi.models.enums.ERoleName;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotNull
    public String fullNames;

    @NotNull
    @Column(unique = true)
    public String email;

    @NotNull
    @Column(unique = true)
    public String username;

    @NotNull
    public String password;

    private ERoleName role;

    public User() { }

    public User(String fullNames, String email, String username, String password) {
        this.fullNames = fullNames;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullNames() {
        return fullNames;
    }

    public void setFullNames(String fullNames) {
        this.fullNames = fullNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ERoleName getRole() {
        return role;
    }

    public void setRole(ERoleName role) {
        this.role = role;
    }
}
