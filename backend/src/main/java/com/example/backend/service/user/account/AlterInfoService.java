package com.example.backend.service.user.account;

import com.example.backend.utils.result.ResultData;

public interface AlterInfoService {
    public ResultData alterInfo(String name, String avatar, String gender, String phone, String email);
}
