package com.example.backend.service.user.account;

import com.example.backend.utils.result.ResultData;

public interface UserRegisterService {
    ResultData<Object> userRegister(String username, String password, String confirmedPassword, String role);
}
