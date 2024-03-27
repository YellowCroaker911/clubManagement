package com.example.backend.service.impl.utils;

import com.example.backend.mapper.UserActivityMapper;
import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserActivity;
import com.example.backend.service.UserActivityService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Long.parseLong;

@Service
public class UserActivityServiceImpl implements UserActivityService {
    @Autowired
    UserActivityMapper userActivityMapper;
    @Override
    public ResultData<Object> userActivitySignUp(String id) {
        UserDetailsImpl userDetails = LoginUser.getUserDetails();
        User loginUser = userDetails.getUser();

        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(loginUser.getId());
        userActivity.setActivityId(parseLong(id));

        userActivityMapper.insert(userActivity);

        return ResultData.success(null);
    }
}
