package com.example.springdatadtoexercise.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserLoginDto {

    private String email;
    private String password;

    public UserLoginDto() {
    }

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "\"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$\"")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
