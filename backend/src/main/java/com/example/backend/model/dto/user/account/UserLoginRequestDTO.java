package com.example.backend.model.dto.user.account;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequestDTO implements Serializable {
    private String username;
    private String password;
//  todo  private String captcha;
}
