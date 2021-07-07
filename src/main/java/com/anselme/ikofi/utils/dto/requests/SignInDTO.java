package com.anselme.ikofi.utils.dto.requests;

import javax.validation.constraints.NotNull;

public class SignInDTO {
    @NotNull
    public String login;

    @NotNull
    public String password;

    public SignInDTO() { }

    public SignInDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
