package com.example.backend.service;

import com.example.backend.utils.result.ResultData;

public interface UserActivityService {
    ResultData<Object> userActivitySignUp(String userId,String id);

    ResultData<Object> userActivityPay(String userId,String id);

    ResultData<Object> userActivitySignIn(String userId,String id);

    ResultData<Object> userActivityDelete(String userId,String activityId);
}
