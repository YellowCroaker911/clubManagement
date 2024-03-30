package com.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.model.entity.Activity;
import com.example.backend.model.entity.UserActivity;
import com.example.backend.model.vo.UserWithUserActivityStateVO;
import com.example.backend.utils.result.ResultData;

import java.util.List;

public interface UserActivityService extends IService<UserActivity> {
    ResultData<Object> userActivitySignUp(String userId,String id);

    ResultData<Object> userActivityPay(String userId,String id);

    ResultData<Object> userActivitySignIn(String userId,String id);

    ResultData<Object> userActivityDelete(String userId,String activityId);

    List<UserWithUserActivityStateVO> listUserByActivityId(Long activityId);
}
