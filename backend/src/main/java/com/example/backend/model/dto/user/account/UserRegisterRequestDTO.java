package com.example.backend.model.dto.user.account;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequestDTO implements Serializable {
    private String username;
    private String password;
    private String confirmedPassword;
    private String role;
}
