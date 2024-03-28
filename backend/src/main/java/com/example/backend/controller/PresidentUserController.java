package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.ActivityMapper;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.dto.activity.ActivityAlterInfoRequestDTO;
import com.example.backend.model.dto.activity.ActivityReleaseRequestDTO;
import com.example.backend.model.dto.club.ClubAlterInfoRequestDTO;
import com.example.backend.model.entity.Activity;
import com.example.backend.model.entity.Club;
import com.example.backend.model.entity.User;
import com.example.backend.service.ActivityService;
import com.example.backend.service.ClubService;
import com.example.backend.service.UserClubService;
import com.example.backend.service.UserService;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static javax.xml.bind.DatatypeConverter.parseLong;

@RestController
@RequestMapping("/president")
public class PresidentUserController {
    @Autowired
    UserService userService;
    @Autowired
    ClubService clubService;
    @Autowired
    UserClubService userClubService;
    @Autowired
    ActivityService activityService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ClubMapper clubMapper;
    @Autowired
    ActivityMapper activityMapper;
    // 修改社团信息
    @PostMapping("/alterInfo")
    public ResultData<Object> clubAlterInfo(@RequestBody @Validated ClubAlterInfoRequestDTO clubAlterInfoRequestDTO){
        return clubService.clubAlterInfo(clubAlterInfoRequestDTO.getId(),clubAlterInfoRequestDTO.getAvatar(),
                clubAlterInfoRequestDTO.getInfo(),clubAlterInfoRequestDTO.getAddress(),clubAlterInfoRequestDTO.getContact());
    }

    // 社员加入通过
    @GetMapping("/pass")
    public ResultData<Object> clubPass(@RequestParam String userId,String clubId){
        return userClubService.userClubPass(userId,clubId);
    }

    // 社长踢人
    @GetMapping("kick")
    public ResultData<Object> userKick(String userId,String clubId){
        return userClubService.userClubDelete(userId,clubId);
    }

    // 活动发布
    @PostMapping("/activityRelease")
    public ResultData<Object> activityRelease(@RequestBody @Validated ActivityReleaseRequestDTO activityReleaseRequestDTO){
        return activityService.activityRelease(activityReleaseRequestDTO.getClubId(),activityReleaseRequestDTO.getName(),
                activityReleaseRequestDTO.getInfo(), activityReleaseRequestDTO.getTitle(),
                activityReleaseRequestDTO.getBeginTime(),activityReleaseRequestDTO.getEndTime(),
                activityReleaseRequestDTO.getAddress(),activityReleaseRequestDTO.getSign(),activityReleaseRequestDTO.getMoney());
    }

    // 活动修改和活动总结发布
    @PostMapping("/activityAlterInfo")
    public ResultData<Object> activityAlterInfo(@RequestBody @Validated ActivityAlterInfoRequestDTO activityAlterInfoRequestDTO) {
        return activityService.activityAlterInfo(activityAlterInfoRequestDTO.getId(),activityAlterInfoRequestDTO.getName(),
                activityAlterInfoRequestDTO.getInfo(), activityAlterInfoRequestDTO.getTitle(),
                activityAlterInfoRequestDTO.getBeginTime(),activityAlterInfoRequestDTO.getEndTime(),
                activityAlterInfoRequestDTO.getAddress(),activityAlterInfoRequestDTO.getSign(),activityAlterInfoRequestDTO.getMoney(),
                activityAlterInfoRequestDTO.getSummary());
    }

    // 活动删除
    @GetMapping("/activityDelete")
    public ResultData<Object> activityDelete(String id){
        return activityService.activityDelete(id);
    }

    // 获取社团管理的社员
    @GetMapping ("/getUsersByClubId")
    public ResultData<List<User>> getUsers(@RequestParam String id){
        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Club club = clubMapper.selectOne(queryWrapper);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }
        List<User> users = userMapper.getUserByClubId(parseLong(id));
        return ResultData.success(users);
    }

    // 获取社团管理的活动
    @GetMapping("getActivitiesByClubId")
    public ResultData<List<Activity>> getActivities(@RequestParam String id){
        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Club club = clubMapper.selectOne(queryWrapper);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }
        List<Activity> activities = activityMapper.getActivitiesByClubId(parseLong(id));
        return ResultData.success(activities);
    }
}
