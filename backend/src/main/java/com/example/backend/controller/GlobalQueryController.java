package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.ActivityMapper;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.entity.Activity;
import com.example.backend.model.entity.Club;
import com.example.backend.model.entity.User;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/globalQuery")
public class GlobalQueryController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ClubMapper clubMapper;
    @Autowired
    ActivityMapper activityMapper;
    @GetMapping("/getAllUser")
    public ResultData<List<User>> getAllUser(){
        return ResultData.success(userMapper.selectList(null));
    }
    @GetMapping("/getAllClub")
    public ResultData<List<Club>> getAllClub(){
        return ResultData.success(clubMapper.selectList(null));
    }

    @GetMapping("/getAllActivity")
    public ResultData<List<Activity>> getAllActivity(){
        return ResultData.success(activityMapper.selectList(null));
    }
}
