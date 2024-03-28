package com.example.backend.controller;

import com.example.backend.model.dto.club.ClubRegisterRequestDTO;
import com.example.backend.model.dto.id.Id1DTO;
import com.example.backend.service.ClubService;
import com.example.backend.service.UserActivityService;
import com.example.backend.service.UserClubService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class commonUserController {
    @Autowired
    ClubService clubService;
    @Autowired
    UserClubService userClubService;
    @Autowired
    UserActivityService userActivityService;
    @PostMapping("/register")
    public ResultData<Object> clubRegister(@RequestBody @Validated ClubRegisterRequestDTO clubRegisterRequestDTO) {
        return clubService.clubRegister(clubRegisterRequestDTO.getName());
    }

    @PostMapping("/join")
    public ResultData<Object> clubJoin(@RequestBody @Validated Id1DTO id1DTO){
        return userClubService.userClubJoin(id1DTO.getId());
    }

    @PostMapping("/signUp")
    public ResultData<Object> activitySignUp(@RequestBody @Validated Id1DTO id1DTO) {
        return userActivityService.userActivitySignUp(id1DTO.getId());
    }

    @PostMapping("/pay")
    public ResultData<Object> activityPay(@RequestBody @Validated Id1DTO id1DTO) {
        return userActivityService.userActivityPay(id1DTO.getId());
    }

    @PostMapping("/signIn")
    public ResultData<Object> activitySignIn(@RequestBody @Validated Id1DTO id1DTO) {
        return userActivityService.userActivitySignIn(id1DTO.getId());
    }

}
