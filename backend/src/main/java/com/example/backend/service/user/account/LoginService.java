package com.example.backend.service.user.account;

import com.example.backend.utils.result.ResultData;

public interface LoginService {
    public ResultData getToken(String username, String password);
}
