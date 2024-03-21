package com.example.backend.service.user.account;

import com.example.backend.utils.result.ResultData;

import java.util.Map;

public interface LoginService {
    public ResultData getToken(String username, String password);
}
