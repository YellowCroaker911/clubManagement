package com.example.backend.service.club;

import com.example.backend.utils.result.ResultData;

public interface ClubRegisterService {
    public ResultData register(String name,String president_id);
}
