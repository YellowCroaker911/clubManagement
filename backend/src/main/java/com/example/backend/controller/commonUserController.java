package com.example.backend.controller;

import com.example.backend.mapper.ActivityMapper;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.model.dto.club.ClubRegisterRequestDTO;
import com.example.backend.model.entity.Activity;
import com.example.backend.model.entity.Club;
import com.example.backend.model.entity.User;
import com.example.backend.service.ClubService;
import com.example.backend.service.UserActivityService;
import com.example.backend.service.UserClubService;
import com.example.backend.service.UserService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/common")
public class commonUserController {
    @Autowired
    UserService userService;
    @Autowired
    ClubService clubService;
    @Autowired
    UserClubService userClubService;
    @Autowired
    UserActivityService userActivityService;
    @Autowired
    ClubMapper clubMapper;
    @Autowired
    ActivityMapper activityMapper;
    // 社团注册
    @PostMapping("/register")
    public ResultData<Object> clubRegister(@RequestBody @Validated ClubRegisterRequestDTO clubRegisterRequestDTO) {
        return clubService.clubRegister(clubRegisterRequestDTO.getName());
    }
    // 社团加入
    @GetMapping("/join")
    public ResultData<Object> clubJoin(@RequestParam String id){
        return userClubService.userClubJoin(id);
    }
    // 活动报名
    @GetMapping("/signUp")
    public ResultData<Object> activitySignUp(@RequestParam String id) {
        return userActivityService.userActivitySignUp(id);
    }
    // 活动缴费
    @GetMapping("/pay")
    public ResultData<Object> activityPay(@RequestParam String id) {
        return userActivityService.userActivityPay(id);
    }
    // 活动签到
    @GetMapping("/signIn")
    public ResultData<Object> activitySignIn(@RequestParam String id) {
        return userActivityService.userActivitySignIn(id);
    }
    // 获取用户已加入社团
    @GetMapping("/getSelfClubs")
    public ResultData<List<Club>> getSelfClubs(){
        User loginUser = userService.userGetSelfInfo();
        List<Club> clubs = clubMapper.getClubsByUserId(loginUser.getId());
        return ResultData.success(clubs);
    }
    // 获取用户作为社长的社团
    @GetMapping("/getSelfClubsAsPresident")
    public ResultData<List<Club>> getSelfClubsAsPresident(){
        User loginUser = userService.userGetSelfInfo();
        List<Club> clubs = clubMapper.getClubsByPresidentId(loginUser.getId());
        return ResultData.success(clubs);
    }
    // 查询用户所在社团活动
    @GetMapping("/getSelfClubActivities")
    public ResultData<List<Activity>> getSelfClubActivities(){
        User loginUser = userService.userGetSelfInfo();
        List<Activity> activities = activityMapper.getActivitiesByUserId(loginUser.getId());
        return ResultData.success(activities);
    }
}
