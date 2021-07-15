package com.anselme.ikofi.utils.dto.requests;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
    @Pattern(regexp = "^[0-9]{5}$")
    String pin;

    @NotBlank
    String password;

    @NotBlank
    String address;

    @NotBlank
    String idCard;

    public SignUpDTO() { }

    public SignUpDTO(String fullName, String email, String username,String pin, String password) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.pin = pin;
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
