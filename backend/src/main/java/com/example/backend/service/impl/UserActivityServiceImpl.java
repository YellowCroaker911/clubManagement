package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.ActivityMapper;
import com.example.backend.mapper.UserActivityMapper;
import com.example.backend.mapper.UserClubMapper;
import com.example.backend.model.entity.Activity;
import com.example.backend.model.entity.UserActivity;
import com.example.backend.model.entity.UserClub;
import com.example.backend.service.UserActivityService;
import com.example.backend.service.UserService;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.lang.Long.parseLong;

@Service
public class UserActivityServiceImpl implements UserActivityService {
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    UserActivityMapper userActivityMapper;
    @Autowired
    UserService userService;
    @Autowired
    UserClubMapper userClubMapper;
    @Override
    public ResultData<Object> userActivitySignUp(String userId, String activityId) {

        QueryWrapper<Activity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", activityId);
        Activity activity = activityMapper.selectOne(queryWrapper1);
        if (activity == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        QueryWrapper<UserActivity> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("user_id",userId).eq("activity_id",activityId);
        List<UserActivity> userActivities = userActivityMapper.selectList(queryWrapper2);
        if (!userActivities.isEmpty()){
            throw new BusinessException(ReturnCodes.SIGN_UP_YET,null);
        }

        QueryWrapper<UserClub> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("user_id",userId).eq("club_id",activity.getClubId());
        UserClub userClub = userClubMapper.selectOne(queryWrapper3);
        if(userClub == null){
            throw new BusinessException(ReturnCodes.NOT_MEMBER,null);
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
            throw new BusinessException(ReturnCodes.NOT_SIGN_UP,null);
        }

        UpdateWrapper<UserActivity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("pay_status",1);
        userActivityMapper.update(userActivity,updateWrapper);

        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> userActivitySignIn(String userId,String activityId) {

        QueryWrapper<UserActivity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id",userId).eq("activity_id", activityId);
        UserActivity userActivity = userActivityMapper.selectOne(queryWrapper1);
        if (userActivity == null) {
            throw new BusinessException(ReturnCodes.NOT_SIGN_UP,null);
        }

        QueryWrapper<Activity> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id",activityId);
        Activity activity = activityMapper.selectOne(queryWrapper2);

        Date date = new Date();

        if(!(activity.getSignBeginTime().before(date) && date.before(activity.getSignEndTime()))){
            throw  new BusinessException(ReturnCodes.NOT_SIGN_TIME,null);
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
