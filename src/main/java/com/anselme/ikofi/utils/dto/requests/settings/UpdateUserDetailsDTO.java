package com.anselme.ikofi.utils.dto.requests.settings;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UpdateUserDetailsDTO {

    @NotBlank
    String fullName;

    @NotBlank
    String email;

    @NotBlank
    String mobile;

    @NotBlank
    String username;

    @NotBlank
    String address;

    @Length(min = 16, max = 16)
    @NotBlank
    String idCard;

    public UpdateUserDetailsDTO() {
    }

    public UpdateUserDetailsDTO(String fullName, String email, String mobile, String username, String address, String idCard) {
        this.fullName = fullName;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.address = address;
        this.idCard = idCard;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
