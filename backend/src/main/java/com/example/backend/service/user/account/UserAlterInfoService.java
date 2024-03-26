package com.example.backend.service.user.account;

import com.example.backend.utils.result.ResultData;

public interface UserAlterInfoService {
    public ResultData<Object> alterInfo(String name, String avatar, String gender, String phone, String email);
}
