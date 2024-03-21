package com.example.backend.service.user.account;

import com.example.backend.utils.result.ResultData;

import java.util.Map;

public interface RegisterService {
    public ResultData register(String username, String password, String confirmedPassword, String role);
}
