package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.ActivityMapper;
import com.example.backend.mapper.UserActivityMapper;
import com.example.backend.model.entity.Activity;
import com.example.backend.model.entity.Club;
import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserActivity;
import com.example.backend.service.UserActivityService;
import com.example.backend.service.impl.utils.LoginUser;
import com.example.backend.service.impl.utils.UserDetailsImpl;
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
    @Override
    public ResultData<Object> userActivitySignUp(String id) {
        UserDetailsImpl userDetails = LoginUser.getUserDetails();
        User loginUser = userDetails.getUser();

        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Activity activity = activityMapper.selectOne(queryWrapper);
        if (activity == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(loginUser.getId());
        userActivity.setActivityId(parseLong(id));

        userActivityMapper.insert(userActivity);

        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> userActivityPay(String id) {

        UserDetailsImpl userDetails = LoginUser.getUserDetails();
        User loginUser = userDetails.getUser();

        QueryWrapper<UserActivity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",loginUser.getId()).eq("activity_id", id);
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
    public ResultData<Object> userActivitySignIn(String id) {
        //todo:规定时间内签到

        UserDetailsImpl userDetails = LoginUser.getUserDetails();
        User loginUser = userDetails.getUser();

        QueryWrapper<UserActivity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",loginUser.getId()).eq("activity_id", id);
        UserActivity userActivity = userActivityMapper.selectOne(queryWrapper);
        if (userActivity == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        UpdateWrapper<UserActivity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("join_status",1);
        userActivityMapper.update(userActivity,updateWrapper);

        return ResultData.success(null);
    }
}
