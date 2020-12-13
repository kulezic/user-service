package com.a2.userservice.dto;

import org.hibernate.validator.constraints.Length;

public class UserLoginDto {

    private String email;
    @Length(min = 8, max = 30)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
