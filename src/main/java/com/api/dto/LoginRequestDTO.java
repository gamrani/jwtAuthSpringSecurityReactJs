package com.api.dto;

import javax.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(@NotBlank String usernameOrEmail, @NotBlank String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
