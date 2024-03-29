package com.example.backend.service;

import com.example.backend.model.entity.Club;
import com.example.backend.model.entity.User;
import com.example.backend.utils.result.ResultData;

import java.util.List;

public interface ClubService {
    ResultData<Object> clubRegister(String userId, String name);
    ResultData<Object> clubAdmit(String id);
    ResultData<Object> clubAlterInfo(String id,String info,String address,String contact);

    ResultData<Object> clubDelete(String id);

    ResultData<Object> clubUpdateAvatar(String id,String avatarUrl);
}
