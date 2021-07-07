package com.anselme.ikofi.utils.dto.responses;

import com.anselme.ikofi.models.User;

public class SignUpResponse {

    public User user;

    public String token;

    public SignUpResponse() { }

    public SignUpResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
