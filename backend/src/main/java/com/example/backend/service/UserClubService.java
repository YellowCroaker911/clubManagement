package com.example.backend.service;

import com.example.backend.model.entity.User;
import com.example.backend.utils.result.ResultData;

import java.util.List;

public interface UserClubService {
    ResultData<Object> userClubJoin(String id);
    ResultData<Object> userClubPass(String userId,String clubId);

}
