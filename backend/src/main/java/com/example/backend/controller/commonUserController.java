package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.ActivityMapper;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.mapper.UserActivityMapper;
import com.example.backend.model.dto.club.ClubRegisterRequestDTO;
import com.example.backend.model.entity.Activity;
import com.example.backend.model.entity.Club;
import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserActivity;
import com.example.backend.model.vo.ActivityWithClubNameVO;
import com.example.backend.model.vo.UserActivityExtendVO;
import com.example.backend.service.ClubService;
import com.example.backend.service.UserActivityService;
import com.example.backend.service.UserClubService;
import com.example.backend.service.UserService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
    @Autowired
    UserActivityMapper userActivityMapper;

    // 社团注册
    @PostMapping("/register")
    public ResultData<Object> clubRegister(@RequestBody @Validated ClubRegisterRequestDTO clubRegisterRequestDTO) {
        User loginUser = userService.userGetSelfInfo();
        return clubService.clubRegister(loginUser.getId().toString(),clubRegisterRequestDTO.getName());
    }

    // 社团加入
    @PostMapping("/join")
    public ResultData<Object> clubJoin(@RequestParam @NotNull String id){
        User loginUser = userService.userGetSelfInfo();
        return userClubService.userClubJoin(loginUser.getId().toString(),id);
    }

    // 社团退出
    @PostMapping("/exit")
    public ResultData<Object> clubExit(@RequestParam @NotNull String id){
        User loginUser = userService.userGetSelfInfo();
        return userClubService.userClubDelete(loginUser.getId().toString(),id);
    }

    // 活动报名
    @PostMapping("/signUp")
    public ResultData<Object> activitySignUp(@RequestParam @NotNull  String id) {
        User loginUser = userService.userGetSelfInfo();
        return userActivityService.userActivitySignUp(loginUser.getId().toString(),id);
    }

    // 活动取消报名
    @PostMapping("/cancel")
    public ResultData<Object> activityCancel(@RequestParam @NotNull String id) {
        User loginUser = userService.userGetSelfInfo();
        return userActivityService.userActivityDelete(loginUser.getId().toString(),id);
    }

    // 活动缴费
    @PostMapping("/pay")
    public ResultData<Object> activityPay(@RequestParam  @NotNull String id) {
        User loginUser = userService.userGetSelfInfo();
        return userActivityService.userActivityPay(loginUser.getId().toString(),id);
    }

    // 活动签到
    @PostMapping("/signIn")
    public ResultData<Object> activitySignIn(@RequestParam  @NotNull String id) {
        User loginUser = userService.userGetSelfInfo();
        return userActivityService.userActivitySignIn(loginUser.getId().toString(),id);
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

    // 获取用户所在社团活动
    @GetMapping("/getSelfClubActivities")
    public ResultData<List<ActivityWithClubNameVO>> getSelfClubActivities(){
        User loginUser = userService.userGetSelfInfo();
        List<Activity> activities = activityMapper.getActivitiesByUserId(loginUser.getId());
        List<ActivityWithClubNameVO> activityWithClubNameVOs = new ArrayList<>();

        for (Activity activity : activities){
            ActivityWithClubNameVO activityWithClubNameVO = new ActivityWithClubNameVO();
            BeanUtils.copyProperties(activity,activityWithClubNameVO);
            QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",activity.getClubId());
            Club club = clubMapper.selectOne(queryWrapper);
            activityWithClubNameVO.setClubName(club.getName());
            activityWithClubNameVOs.add(activityWithClubNameVO);
        }

        return ResultData.success(activityWithClubNameVOs);
    }

    // 获取用户报名的活动
    @GetMapping("/getSelfActivityById")
    public ResultData<UserActivityExtendVO> getSelfActivityById(@RequestParam @NotNull String activityId) {
        User loginUser = userService.userGetSelfInfo();
        UserActivityExtendVO userActivityExtendVO = new UserActivityExtendVO();

        QueryWrapper<UserActivity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id",loginUser.getId()).eq("activity_id",activityId);
        UserActivity userActivity = userActivityMapper.selectOne(queryWrapper1);
        BeanUtils.copyProperties(userActivity,userActivityExtendVO);

        QueryWrapper<Activity> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id",activityId);
        Activity activity = activityMapper.selectOne(queryWrapper2);
        BeanUtils.copyProperties(activity,userActivityExtendVO);

        QueryWrapper<Club> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("id",activity.getClubId());
        Club club = clubMapper.selectOne(queryWrapper3);
        userActivityExtendVO.setClubName(club.getName());

        return ResultData.success(userActivityExtendVO);
    }
}
