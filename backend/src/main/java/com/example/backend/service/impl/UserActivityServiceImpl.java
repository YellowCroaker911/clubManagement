package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.ActivityMapper;
import com.example.backend.mapper.UserActivityMapper;
import com.example.backend.model.entity.Activity;
import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserActivity;
import com.example.backend.service.UserActivityService;
import com.example.backend.service.UserService;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Long.parseLong;

@Service
public class UserActivityServiceImpl implements UserActivityService {
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    UserActivityMapper userActivityMapper;
    @Autowired
    UserService userService;
    @Override
    public ResultData<Object> userActivitySignUp(String userId, String activityId) {

        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", activityId);
        Activity activity = activityMapper.selectOne(queryWrapper);
        if (activity == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(parseLong(userId));
        userActivity.setActivityId(parseLong(activityId));

        userActivityMapper.insert(userActivity);

        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> userActivityPay(String userId,String activityId) {

        QueryWrapper<UserActivity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("activity_id", activityId);
        UserActivity userActivity = userActivityMapper.selectOne(queryWrapper);
        if (userActivity == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        UpdateWrapper<UserActivity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("pay_status",1);
        userActivityMapper.update(userActivity,updateWrapper);

        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> userActivitySignIn(String userId,String activityId) {
        //todo:规定时间内签到

        QueryWrapper<UserActivity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("activity_id", activityId);
        UserActivity userActivity = userActivityMapper.selectOne(queryWrapper);
        if (userActivity == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        UpdateWrapper<UserActivity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("join_status",1);
        userActivityMapper.update(userActivity,updateWrapper);

        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> userActivityDelete(String userId, String activityId) {
        QueryWrapper<UserActivity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("activity_id", activityId);
        UserActivity userActivity = userActivityMapper.selectOne(queryWrapper);
        if (userActivity == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        userActivityMapper.delete(queryWrapper);

        return ResultData.success(null);
    }
}
