package com.example.backend.controller;

import com.example.backend.mapper.ActivityMapper;
import com.example.backend.model.dto.activity.ActivityReleaseRequestDTO;
import com.example.backend.model.dto.club.ClubAlterInfoRequestDTO;
import com.example.backend.model.dto.id.Id1DTO;
import com.example.backend.model.entity.User;
import com.example.backend.service.ActivityService;
import com.example.backend.service.ClubService;
import com.example.backend.service.UserClubService;
import com.example.backend.service.UserService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/alterInfo")
    public ResultData<Object> clubAlterInfo(@RequestBody @Validated ClubAlterInfoRequestDTO clubAlterInfoRequestDTO){
        return clubService.clubAlterInfo(clubAlterInfoRequestDTO.getId(),clubAlterInfoRequestDTO.getAvatar(),
                clubAlterInfoRequestDTO.getInfo(),clubAlterInfoRequestDTO.getAddress(),clubAlterInfoRequestDTO.getContact());
    }

    @PostMapping("/pass")
    public ResultData<Object> clubJoin(@RequestBody @Validated Id1DTO id1DTO){
        return userClubService.userClubJoin(id1DTO.getId());
    }

    /**
     * 返回社团下的所有成员
     * @param id1DTO 社团id
     * @return
     */
    @PostMapping("/checkMember")
    public ResultData<List<User>> checkMember(@RequestBody @Validated Id1DTO id1DTO){
        return userService.userCheckMember(id1DTO.getId());
    }

    @PostMapping("/activityRelease")
    public ResultData<Object> activityRelease(@RequestBody @Validated ActivityReleaseRequestDTO activityReleaseRequestDTO){
        return activityService.activityRelease(activityReleaseRequestDTO.getClubId(),activityReleaseRequestDTO.getName(),
                activityReleaseRequestDTO.getInfo(), activityReleaseRequestDTO.getTitle(),
                activityReleaseRequestDTO.getBeginTime(),activityReleaseRequestDTO.getEndTime(),
                activityReleaseRequestDTO.getAddress(),activityReleaseRequestDTO.getSign(),activityReleaseRequestDTO.getMoney());
    }
}
