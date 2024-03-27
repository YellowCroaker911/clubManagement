package com.example.backend.service;

import com.example.backend.utils.result.ResultData;

public interface UserClubService {
    ResultData<Object> userClubJoin(String id);
    ResultData<Object> userClubPass(String userId,String clubId);
}
