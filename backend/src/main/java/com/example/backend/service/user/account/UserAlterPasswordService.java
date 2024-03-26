package com.example.backend.service.user.account;

import com.example.backend.utils.result.ResultData;

public interface UserAlterPasswordService {
    public ResultData<Object> alterPassword(String passwrd,String confirmedPassword);
}
