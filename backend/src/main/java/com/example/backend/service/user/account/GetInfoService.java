package com.example.backend.service.user.account;

import com.example.backend.model.pojo.User;
import com.example.backend.utils.result.ResultData;


public interface GetInfoService {
    ResultData<User> getInfo();
}
