package com.example.backend.service;

import com.example.backend.utils.result.ResultData;

public interface UserActivityService {
    ResultData<Object> userActivitySignUp(String id);

    ResultData<Object> userActivityPay(String id);

    ResultData<Object> userActivitySignIn(String id);


}
