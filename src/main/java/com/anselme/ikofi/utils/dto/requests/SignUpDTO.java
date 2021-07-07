package com.anselme.ikofi.utils.dto.requests;

import javax.validation.constraints.NotBlank;

public class SignUpDTO {
    @NotBlank
    String fullName;

    @NotBlank
    String email;

    @NotBlank
    String mobile;

    @NotBlank
    String username;

    @NotBlank
    String password;

    public SignUpDTO() { }

    public SignUpDTO(String fullName, String email, String username, String password) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
}
