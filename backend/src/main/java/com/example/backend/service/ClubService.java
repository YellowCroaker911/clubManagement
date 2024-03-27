package com.example.backend.service;

import com.example.backend.model.entity.Club;
import com.example.backend.utils.result.ResultData;

public interface ClubService {
    public ResultData<Object> clubRegister(String name, String president_id);
    public ResultData<Object> clubAdmit(String id);
    public ResultData<Object> clubAlterInfo(String id,String avatar,String info,String address,String contact);
    public ResultData<Club> clubGetInfo(String id);
}
