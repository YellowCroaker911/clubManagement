package com.example.backend.service;

import com.example.backend.model.entity.Club;
import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserClub;
import com.example.backend.utils.result.ResultData;

import java.util.List;

public interface UserClubService {
    ResultData<Object> userClubJoin(String userId,String clubId);
    ResultData<Object> userClubPass(String userId,String clubId);

    ResultData<Object> userClubDelete(String userId,String clubId);

    List<UserClub> getListByUserId(Long userId);
}
