package com.example.backend.service.club;

import com.example.backend.utils.result.ResultData;

public interface ClubRegisterService {
    public ResultData<Object> clubRegister(String name, String president_id);
}
