package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.*;
import com.example.backend.model.entity.*;
import com.example.backend.model.vo.ActivityWithClubNameVO;
import com.example.backend.model.vo.UserActivityExtendVO;
import com.example.backend.model.vo.UserClubExtendClubVO;
import com.example.backend.model.vo.UserClubExtendUserVO;
import com.example.backend.utils.CommonConstant;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.xml.bind.DatatypeConverter.parseLong;

@RestController
@RequestMapping("/globalQuery")
public class GlobalQueryController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ClubMapper clubMapper;
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    UserActivityMapper userActivityMapper;
    @Autowired
    UserClubMapper userClubMapper;

    // 获取所有用户
    @GetMapping("/getAllUser")
    public ResultData<List<User>> getAllUser() {
        return ResultData.success(userMapper.selectList(null));
    }

    // 获取社团管理的用户
    @GetMapping("/getUserClubExtendUser")
    public ResultData<List<UserClubExtendUserVO>> getUserClubExtendUser(@RequestParam @NotNull String clubId) {

        List<UserClubExtendUserVO> userClubExtendUserVOS = new ArrayList<>();

        QueryWrapper<Club> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", clubId);
        Club club = clubMapper.selectOne(queryWrapper1);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }

        QueryWrapper<UserClub> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("club_id", clubId);
        List<UserClub> userClubs = userClubMapper.selectList(queryWrapper2);

        for (UserClub userClub : userClubs) {
            UserClubExtendUserVO userClubExtendUserVO = new UserClubExtendUserVO();
            BeanUtils.copyProperties(userClub, userClubExtendUserVO);

            QueryWrapper<User> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.eq("id", userClub.getUserId());
            User user = userMapper.selectOne(queryWrapper3);
            BeanUtils.copyProperties(user, userClubExtendUserVO);

            userClubExtendUserVOS.add(userClubExtendUserVO);
        }

        return ResultData.success(userClubExtendUserVOS);
    }

    // 获取所有社团
    @GetMapping("/getAllClub")
    public ResultData<List<Club>> getAllClub() {
        return ResultData.success(clubMapper.selectList(null));
    }

    // 获取社团信息
    @GetMapping("/getClubById")
    public ResultData<Club> getClubById(@RequestParam @NotNull String id) {
        return ResultData.success(clubMapper.selectById(id));
    }

    // 获取所有活动
    @GetMapping("/getAllActivity")
    public ResultData<List<ActivityWithClubNameVO>> getAllActivity() {

        List<ActivityWithClubNameVO> activityWithClubNameVOs = new ArrayList<>();
        List<Activity> activities = activityMapper.selectList(null);

        for (Activity activity : activities) {
            ActivityWithClubNameVO activityWithClubNameVO = new ActivityWithClubNameVO();
            BeanUtils.copyProperties(activity, activityWithClubNameVO);
            QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", activity.getClubId());
            Club club = clubMapper.selectOne(queryWrapper);
            activityWithClubNameVO.setClubName(club.getName());
            activityWithClubNameVOs.add(activityWithClubNameVO);
        }

        return ResultData.success(activityWithClubNameVOs);
    }

    // 获取社团管理的活动
    @GetMapping("getActivitiesByClubId")
    public ResultData<List<Activity>> getActivities(@RequestParam @NotNull String id) {
        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Club club = clubMapper.selectOne(queryWrapper);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }
        List<Activity> activities = activityMapper.getActivitiesByClubId(parseLong(id));
        return ResultData.success(activities);
    }

    // 获取活动
    @GetMapping("/getActivityById")
    public ResultData<ActivityWithClubNameVO> getActivityById(@RequestParam @NotNull String id) {
        Activity activity = activityMapper.selectById(id);
        if (activity == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }

        ActivityWithClubNameVO activityWithClubNameVO = new ActivityWithClubNameVO();
        BeanUtils.copyProperties(activity, activityWithClubNameVO);

        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", activity.getClubId());
        Club club = clubMapper.selectOne(queryWrapper);
        activityWithClubNameVO.setClubName(club.getName());
        return ResultData.success(activityWithClubNameVO);
    }

    // 获取用户报名的活动
    @GetMapping("/getUserActivityById")
    public ResultData<UserActivityExtendVO> getUserActivityById(@RequestParam @NotNull String userId,@RequestParam @NotNull String activityId) {
        UserActivityExtendVO userActivityExtendVO = new UserActivityExtendVO();

        QueryWrapper<UserActivity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id", userId).eq("activity_id", activityId);
        UserActivity userActivity = userActivityMapper.selectOne(queryWrapper1);
        if (userActivity == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }

        BeanUtils.copyProperties(userActivity, userActivityExtendVO);

        QueryWrapper<Activity> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id", activityId);
        Activity activity = activityMapper.selectOne(queryWrapper2);
        BeanUtils.copyProperties(activity, userActivityExtendVO);

        QueryWrapper<Club> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("id", activity.getClubId());
        Club club = clubMapper.selectOne(queryWrapper3);
        userActivityExtendVO.setClubName(club.getName());

        return ResultData.success(userActivityExtendVO);
    }

    // 获取所有*用户和社团*的关系
    @GetMapping ("/getUserClubExtendClub")
    public ResultData<List<UserClubExtendClubVO>> getUserClubExtendClub(@RequestParam @NotNull String clubId) {

        List<UserClubExtendClubVO> userClubExtendClubVOS = new ArrayList<>();

        QueryWrapper<Club> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", clubId);
        Club club = clubMapper.selectOne(queryWrapper1);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }

        QueryWrapper<UserClub> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("club_id", clubId);
        List<UserClub> userClubs = userClubMapper.selectList(queryWrapper2);

        for (UserClub userClub : userClubs) {
            UserClubExtendClubVO userClubExtendClubVO = new UserClubExtendClubVO();
            BeanUtils.copyProperties(userClub, userClubExtendClubVO);
            BeanUtils.copyProperties(club, userClubExtendClubVO);
            userClubExtendClubVOS.add(userClubExtendClubVO);
        }

        return ResultData.success(userClubExtendClubVOS);
    }

    /**
     * 获取社团头像，返回base64
     *
     * @return
     */
    @GetMapping("/getClubAvatar")
    public ResultData<String> getClubAvatar(@RequestParam @NotNull String id) {
        Club club = clubMapper.selectById(id);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }
        // 获取图片名称
        String avatar = club.getAvatar();
        // 获取图片并转成Base64
        return ResultData.success(CommonUtil.convertImageToBase64Str(CommonConstant.IMG_FOLDER + avatar));
    }
}
