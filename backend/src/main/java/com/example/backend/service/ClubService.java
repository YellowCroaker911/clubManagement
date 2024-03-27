package com.example.backend.service;

import com.example.backend.model.entity.Club;
import com.example.backend.utils.result.ResultData;

public interface ClubService {
    ResultData<Object> clubRegister(String name, String president_id);
    ResultData<Object> clubAdmit(String id);
    ResultData<Object> clubAlterInfo(String id,String avatar,String info,String address,String contact);
    ResultData<Club> clubGetInfo(String id);
}
