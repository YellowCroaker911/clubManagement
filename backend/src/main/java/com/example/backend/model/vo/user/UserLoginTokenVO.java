package com.example.backend.model.vo.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginTokenVO implements Serializable {
    private String token;
}
